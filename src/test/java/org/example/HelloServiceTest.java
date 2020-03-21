package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private HelloService SUT = new HelloService();

    @Test
    public void test_prepareGreeting_null_returnsDefaultValue() {
        // given + when
        String result = SUT.prepareGreeting(null);

        // then
        assertEquals("Hello " + HelloService.DEFAULT_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsName() {
        // given
        String name = "testName";

        // when
        String result = SUT.prepareGreeting(name);

        // then
        assertEquals("Hello " + name + "!", result);
    }
}
