package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {
    static final String DEFAULT_NAME = "stranger";
    static final Lang DEFAULT_LANG = new Lang(1L, "Hello ", "en");
    private final Logger log = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang){
        Long langId;
        try {
            langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(DEFAULT_LANG.getId());
        } catch (NumberFormatException e) {
            log.warn("Non-numeric language id used " + lang);
            langId = DEFAULT_LANG.getId();
        }
        String welcomeMsg = repository.findById(langId).orElse(DEFAULT_LANG).getWelcomeMsg();
        String welcomeName = Optional.ofNullable(name).orElse(DEFAULT_NAME);

        return welcomeMsg + " " +  welcomeName + "!";
    }
}
