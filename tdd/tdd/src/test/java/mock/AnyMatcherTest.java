package mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AnyMatcherTest {

    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertThat(num).isEqualTo("456");

        String num2 = genMock.generate(GameLevel.NORMAL);
        Assertions.assertThat(num2).isEqualTo("456");
    }

    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        Assertions.assertThat(old).isEqualTo("456");
    }
}
