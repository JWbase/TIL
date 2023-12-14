package mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        //정확하게 해당 메소드가 검증 됐는지 알아 보려면
        then(genMock).should().generate(GameLevel.EASY);

        //메서드 호출 여부를 알고 싶을 때
        then(genMock).should().generate(any());

        //정확하게 한번만 메소드 호출이 된 것을 검증하고 싶을 때
        then(genMock).should(only()).generate(any());
    }
}
