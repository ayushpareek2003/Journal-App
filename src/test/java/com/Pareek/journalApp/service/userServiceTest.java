package com.Pareek.journalApp.service;

import com.Pareek.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class userServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Disabled // this will not run now
    public void test() {

        assertNotNull(userRepo.findByUsername("ayush"),"yha message dall0");
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12"
    })
    public void testFindByUsernameAndPassword(int a, int b,int exp) {
        assertEquals(exp,a+b);
    }

}
