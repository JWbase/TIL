package chap03;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * 서비스를 사용하려면 매달 1만 원을 선불로 납부한다.
 * 납무일 기준으로 한 달 뒤가 서비스 만료일 2개월 이상 요금을 납부할 수 있다.
 * 10만 원을 납부하면 서비스를 1년 제공한다.
 */
class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2023, 12, 13);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertThat(expiryDate).isEqualTo(LocalDate.of(2024, 1, 13));
    }
}
