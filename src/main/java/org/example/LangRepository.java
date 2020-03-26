package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {
    private List<Lang> languages;

    LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1, "Hello ", "en"));
        languages.add(new Lang(2, "Witaj ", "pl"));
        languages.add(new Lang(3, "Hallo ", "de"));
    }

    Optional<Lang> findById(Integer id) {
        return languages
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }
}
