package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.ChangePasswordDTO;
import com.daemonide.expensetracker.dto.UpdateAccountDTO;
import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.repository.UserRepository;
import com.daemonide.expensetracker.util.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String updateAccount(
            String currentUsername,
            UpdateAccountDTO request
    ) {
        AppUser user = userRepository
                .findByUsername(currentUsername)
                .orElseThrow();
        
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return "Account updated";
    }

    public String changePassword(
            String currentUsername,
            ChangePasswordDTO request
    ) {
        AppUser user = userRepository
                .findByUsername(currentUsername)
                .orElseThrow();

        boolean matches = encoder.matches(
                request.getCurrentPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Current password is incorrect");
        }

        if (!PasswordValidator.isValid(request.getNewPassword())) {
            throw new RuntimeException(
                    "Password must contain uppercase, lowercase, number, special character and be at least 8 characters long"
            );
        }

        user.setPassword(
                encoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        return "Password updated";
    }
}