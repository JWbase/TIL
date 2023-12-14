package mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JUnit5ExtensionTest {

    //@Mock 어노테이션이 붙인 필드에 대해 자동으로 모의 객체 생성
    @Mock
    private GameNumGen genMock;
}
