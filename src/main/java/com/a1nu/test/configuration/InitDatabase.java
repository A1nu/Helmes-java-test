package com.a1nu.test.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.a1nu.test.configuration.defaultDatabaseData.Selectors;
import com.a1nu.test.entity.Selector;
import com.a1nu.test.service.SelectorService;

@Component
public class InitDatabase implements ApplicationRunner {
    @Autowired
    private SelectorService selectorService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Object[][] defaultSelectors = Selectors.getDefaultSelectors();
        try {
            List<Selector> selectors = selectorService.getSelectors();
            if (!Arrays.stream(defaultSelectors).allMatch(e->selectors.stream().map(Selector::getId).anyMatch(rr->rr.equals((Integer) e[0])))) {
                throw new IllegalStateException("no selectors");
            }
        } catch (IllegalStateException ex) {
            System.out.println("Creating default selectors");
            Arrays.stream(defaultSelectors).forEach(s -> {
                Selector selector = new Selector();
                selector.setId((Integer) s[0]);
                selector.setName((String) s[1]);
                selectorService.save(selector);
            });
            System.out.println("Default selectors created");
        }
        
        System.out.println("Application loaded success. Listen on: http://localhost:8080");
    }
}
