package org.example;

import java.util.Optional;

class HelloService {
    static final String DEFAULT_NAME = "stranger";
    static final Lang DEFAULT_LANG = new Lang(1L, "Hello ", "en");

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name){
        return prepareGreeting(name, null);
    }

    String prepareGreeting(String name, String lang){
        Long langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(DEFAULT_LANG.getId());
        String welcomeMsg = repository.findById(langId).orElse(DEFAULT_LANG).getWelcomeMsg();
        String welcomeName = Optional.ofNullable(name).orElse(DEFAULT_NAME);

        return welcomeMsg + " " +  welcomeName + "!";
    }
}
