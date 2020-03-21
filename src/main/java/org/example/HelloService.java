package org.example;

import java.util.Optional;

class HelloService {
    static final String DEFAULT_NAME = "stranger";

    String prepareGreeting(String name){
        return "Hello " + Optional.ofNullable(name).orElse(DEFAULT_NAME) + "!";
    }
}
