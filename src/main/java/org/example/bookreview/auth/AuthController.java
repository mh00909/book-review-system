package org.example.bookreview.auth;

import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.model.ERole;
import org.example.bookreview.model.User;
import org.example.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling registration and authentication.
 */

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor for injecting dependencies.
     * @param authenticationManager
     * @param jwtUtils
     * @param userRepository
     * @param passwordEncoder
     * @param userDetailsService
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                          UserRepository userRepository, PasswordEncoder passwordEncoder,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Authenticates a user based on provided credentails.
     * @param authRequest containing username and password
     * @return ResponseEntity with a JWT token (success) or an error message (failure)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    /**
     * Registers a new user.
     * @param registerRequest containing user details.
     * @return ResponseEntity with a JWT Token (success) or an error message (failure)
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Username already exists"));
        }
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Email already exists"));
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(ERole.ROLE_USER);
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(registerRequest.getUsername());
            String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Authentication failed after registration"));
        }
    }
}

/**
 * Data Transfer Objects
 */

class AuthRequest {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
}

class RegisterRequest {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
}

@Getter @Setter
class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}

@Getter @Setter
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}