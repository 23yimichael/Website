package com.michaelhyi.post;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post not found.");
    }
}
