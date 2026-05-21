package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.LoginRequestDTO;
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

    public String register(RegisterRequestDTO request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("This username is taken");
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword())); // must encode
        userRepository.save(user);

        return "User Registered";
    }

    public String login(LoginRequestDTO request){

        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidLoginException("Invalid Username"));

        boolean matches = encoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches){
            throw new InvalidLoginException("Invalid Password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}