package org.example.hello;

import org.example.lang.Lang;
import org.example.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {
    static final String DEFAULT_NAME = "stranger";
    static final Lang DEFAULT_LANG = new Lang(1, "Hello ", "en");
    private final Logger log = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId){
        langId = Optional.ofNullable(langId).orElse(DEFAULT_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(DEFAULT_LANG).getWelcomeMsg();
        var welcomeName = Optional.ofNullable(name).orElse(DEFAULT_NAME);

        return welcomeMsg + " " +  welcomeName + "!";
    }
}
