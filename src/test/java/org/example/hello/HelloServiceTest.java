package org.example.hello;

import org.example.lang.Lang;
import org.example.lang.LangRepository;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello ";
    private final static String DEFAULT_WELCOME_MSG = "Hi";

    @Test
    public void test_prepareGreeting_nullName_returnsDefaultValue() {
        // given
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);

        // when
        String result = SUT.prepareGreeting(null, -1);

        // then
        assertEquals(WELCOME + " " + HelloService.DEFAULT_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsName() {
        // given
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name = "testName";

        // when
        String result = SUT.prepareGreeting(name, -1);

        // then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsIdLang() {
        // given
        LangRepository mockRepository = defaultLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);

        // when
        String result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(DEFAULT_WELCOME_MSG + " " + HelloService.DEFAULT_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsDefaultLang() {
        // given
        LangRepository mockRepository = nonLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);

        // when
        String result = SUT.prepareGreeting(null, -1);

        // then
        assertEquals(HelloService.DEFAULT_LANG.getWelcomeMsg() + " " + HelloService.DEFAULT_NAME + "!", result);
    }

    private LangRepository nonLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
    }

    private LangRepository defaultLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if(id.equals(HelloService.DEFAULT_LANG.getId())){
                    return Optional.of(new Lang(null, DEFAULT_WELCOME_MSG, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
