package com.learning.spring.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        //when use spring security then steps :
        // get the spring context
        // get the authentication
        // get the principle
        // get the username
        return Optional.of("admin");
    }
}
