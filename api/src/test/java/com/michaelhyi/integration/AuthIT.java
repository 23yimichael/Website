package com.michaelhyi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.michaelhyi.Jwt.JwtService;
import com.michaelhyi.dao.UserRepository;
import com.michaelhyi.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-it.properties")
class AuthIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;
    
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void login() throws Exception {
        User alreadyExists = new User("alreadyexists@mail.com");
        repository.save(alreadyExists);

        String res = mvc.perform(post("/v1/auth/alreadyexists@mail.com")
                                    .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        assertTrue(jwtService.isTokenValid(res, alreadyExists));
        assertEquals(alreadyExists.getEmail(), jwtService.extractUsername(res));

        mvc.perform(post("/v1/auth/unauthorized@mail.com")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());

        res = mvc.perform(post("/v1/auth/test@mail.com")
                                    .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        User expected = repository
                            .findById("test@mail.com")
                            .get();
        assertTrue(jwtService.isTokenValid(res, expected));
        assertEquals(expected.getEmail(), jwtService.extractUsername(res));
    }

    @Test
    void validateToken() throws Exception {
        User user = new User("test@mail.com"); 
        String token = jwtService.generateToken(user);

        mvc.perform(get("/v1/auth/validate-token/" + token))
                .andExpect(status().isNotFound());
        
        repository.save(user);

        mvc.perform(get("/v1/auth/validate-token/" + token))
                .andExpect(status().isOk());
    }
}
