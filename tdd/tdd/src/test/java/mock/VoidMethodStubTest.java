package mock;

import static org.mockito.Mockito.mock;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

class VoidMethodStubTest {

    @Test
    void voidMethodWillThrowTest() {
        List mockList = mock(List.class);
        BDDMockito.willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        Assertions.assertThatThrownBy(mockList::clear)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
