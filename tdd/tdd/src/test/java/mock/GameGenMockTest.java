package mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameGenMockTest {

    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertThat(num).isEqualTo("123");
    }

    @Test
    void mockTest2() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.NORMAL);
        Assertions.assertThat(num).isNull();
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(new IllegalArgumentException());

        Assertions.assertThatThrownBy(() -> genMock.generate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
