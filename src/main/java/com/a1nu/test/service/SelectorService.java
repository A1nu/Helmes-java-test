package com.a1nu.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a1nu.test.entity.Selector;
import com.a1nu.test.repository.SelectorRepository;

@Service("selectorService")
public class SelectorService {
    
    private SelectorRepository selectorRepository;
    
    @Autowired
    public SelectorService(SelectorRepository selectorRepository) {
        this.selectorRepository = selectorRepository;
    }
    
    public List<Selector> getSelectors() {
        return selectorRepository.findAll();
    }
    
    public Selector save(Selector selector) {
        return selectorRepository.save(selector);
    }
}
