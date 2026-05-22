package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.exception.InvalidLoginException;
import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.repository.UserRepository;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws InvalidLoginException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidLoginException("User not found"));
    }

    public AppUser getCurrentUser() {
        Object principal = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof AppUser) {
            return (AppUser) principal;
        }
        throw new org.springframework.security.authentication.BadCredentialsException("User not authenticated");
    }
}
