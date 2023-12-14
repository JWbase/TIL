package mock;

import static org.mockito.Mockito.mock;

import chap07.user.EmailNotifier;
import chap07.user.MemoryUserRepository;
import chap07.user.StubWeakPasswordChecker;
import chap07.user.UserRegister;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

class UserRegisterMockTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, new MemoryUserRepository(), mockEmailNotifier);
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        Assertions.assertThat(realEmail).isEqualTo("email@email.com");
    }
}
