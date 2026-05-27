package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.AuthResponseDTO;
import com.daemonide.expensetracker.dto.LoginRequestDTO;
import com.daemonide.expensetracker.dto.RefreshRequestDTO;
import com.daemonide.expensetracker.dto.RegisterRequestDTO;
import com.daemonide.expensetracker.exception.InvalidLoginException;
import com.daemonide.expensetracker.exception.UserAlreadyExistsException;
import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final TurnstileService turnstileService;

    public String register(RegisterRequestDTO request) {
        if (!turnstileService.verify(request.getCaptchaToken())) {
            throw new InvalidLoginException("Captcha verification failed");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("This username is taken");
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword())); // must encode
        userRepository.save(user);

        return "User Registered";
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidLoginException("Invalid Username"));

        boolean matches = encoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new InvalidLoginException("Invalid Password");
        }

        String accessToken = jwtService.generateToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return new AuthResponseDTO(accessToken, refreshToken);
    }

    public AuthResponseDTO refresh(RefreshRequestDTO request) {

        try {
            String refreshToken = request.getRefreshToken();

            String username =
                    jwtService.extractUsername(refreshToken);

            if (jwtService.isTokenExpired(refreshToken)) {
                throw new RuntimeException("Refresh token expired");
            }

            String newAccessToken =
                    jwtService.generateToken(username);

            return new AuthResponseDTO(
                    newAccessToken,
                    refreshToken
            );

        } catch (Exception e) {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}