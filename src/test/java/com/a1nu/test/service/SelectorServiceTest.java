package com.a1nu.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.a1nu.test.entity.Selector;
import com.a1nu.test.repository.SelectorRepository;

public class SelectorServiceTest {
    @Mock
    private SelectorRepository mockSelectorRepository;
    
    private SelectorService selectorServiceUnderTest;
    
    private Selector selector;
    
    @Before
    public void setUp() {
        initMocks(this);
        selectorServiceUnderTest = new SelectorService(mockSelectorRepository);
        
        selector = Selector.builder()
                .id(1)
                .name("Tester functionality")
                .build();
        
        Mockito.when(mockSelectorRepository.save(any()))
                .thenReturn(selector);
    }
    
    @Test
    public void testSaveUser() {
        // Setup
        final String name = "Tester functionality";
        
        // Run the test
        Selector result = selectorServiceUnderTest.save(Selector.builder().build());
        
        // Verify the results
        assertEquals(name, result.getName());
    }
}
