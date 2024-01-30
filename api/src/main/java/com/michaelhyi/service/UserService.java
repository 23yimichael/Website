package com.michaelhyi.service;

import org.springframework.stereotype.Service;

import com.michaelhyi.dao.UserRepository;
import com.michaelhyi.entity.User;
import com.michaelhyi.exception.UserNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User readUserByEmail(String email)
            throws IllegalArgumentException, UserNotFoundException {
        if (email == null
                || email.isBlank()
                || email.isEmpty()
                || email.equals("null")
                || email.equals("undefined")) {
            throw new IllegalArgumentException();
        }

        return repository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
