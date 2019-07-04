package com.a1nu.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.a1nu.test.entity.User;
import com.a1nu.test.repository.UserRepository;

public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    
    private UserService userServiceUnderTest;
    
    private User user;
    
    
    
    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository);
        user = User.builder()
                .id(1)
                .name("Gustavo")
                .build();
        
        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findUserById(anyInt()))
                .thenReturn(user);
    }
    
    @Test
    public void testFindUserById() {
        // Setup
        final Integer id = 1;
        
        // Run the test
        final User result = userServiceUnderTest.getUserById(id);
        
        // Verify the results
        assertEquals(id, (Integer) result.getId());
    }
    
    @Test
    public void testSaveUser() {
        // Setup
        final String name = "Gustavo";
        
        // Run the test
        User result = userServiceUnderTest.save(User.builder().build());
        
        // Verify the results
        assertEquals(name, result.getName());
    }
}
