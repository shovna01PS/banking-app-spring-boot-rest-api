package com.sapient.app.auth;

import com.sapient.app.config.JwtService;
import com.sapient.app.model.Customer;
import com.sapient.app.model.Role;
import com.sapient.app.model.Token;
import com.sapient.app.model.TokenType;
import com.sapient.app.repository.CustomerRepository;
import com.sapient.app.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository cRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    public AuthenticationResponse register(RegisterRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .aadhaar(request.getAadhaar())
                .phone(request.getPhone())
                .balance(request.getBalance())
                .gender(request.getGender())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();
        cRepo.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Customer customer = cRepo.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(customer);
        revokeAllUserTokens(customer);
        saveJwtToken(customer,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveJwtToken(Customer customer, String jwtToken) {
        Token token = Token.builder()
                .customer(customer)
                .expired(false)
                .revoked(false)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(Customer customer) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(customer.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
