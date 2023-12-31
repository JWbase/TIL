package chap03;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 4, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 2, 28));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2023, 1, 1))
                .billingDate(LocalDate.of(2023, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2023, 3, 1));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2023, 1, 30))
                .billingDate(LocalDate.of(2023, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2023, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2023, 5, 31))
                .billingDate(LocalDate.of(2023, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2023, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2023, 5, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2023, 6, 1));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 1, 31))
                        .billingDate(LocalDate.of(2023, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2023, 4, 30));

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 1, 31))
                        .billingDate(LocalDate.of(2023, 2, 28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2023, 6, 30));

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 3, 31))
                        .billingDate(LocalDate.of(2023, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2023, 7, 31));
    }

    private static void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }

    @Test
    void 십만원을_납부하면_1년제공() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2024, 1, 28));
    }

    @Test
    void 십삼만원을_납부하면_1년_3개월제공() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 28))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2024, 4, 28));
    }

    @Test
    void 윤달에_십만원을_납부할때_만료일_계산() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2024, 2, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2025, 2, 28));
    }
}
