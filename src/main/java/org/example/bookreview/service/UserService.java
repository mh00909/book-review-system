package org.example.bookreview.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.bookreview.DTOs.ReviewMapper;
import org.example.bookreview.DTOs.UserProfileDTO;
import org.example.bookreview.auth.JwtUtils;
import org.example.bookreview.model.Book;
import org.example.bookreview.model.ERole;
import org.example.bookreview.model.Review;
import org.example.bookreview.model.User;
import org.example.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public List<UserProfileDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserProfileDTO::new).collect(Collectors.toList());
    }

    public UserProfileDTO getUserById(Long id) {
        return new UserProfileDTO(userRepository.findById(id).orElse(null));
    }

    public UserProfileDTO createUser(User user) {
        return new UserProfileDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserProfileDTO addReadBook(Long userId, Book book) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getReadBooks().add(book);
        return new UserProfileDTO(userRepository.save(user));
    }

    public UserProfileDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(ERole.ROLE_USER);
        return new UserProfileDTO(userRepository.save(user));
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public UserProfileDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new UserProfileDTO(userRepository.findByUsername(userDetails.getUsername()));
    }

    public UserProfileDTO getUserProfile(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        if (user.isPresent()) {
            userProfileDTO.setId(user.get().getId());
            userProfileDTO.setFirstName(user.get().getFirstName());
            userProfileDTO.setLastName(user.get().getLastName());

            List<Review> reviews = user.get().getReviews();
            userProfileDTO.setReviews(reviews.stream().map(ReviewMapper::toDTO).collect(Collectors.toList()));
        }
        return userProfileDTO;
    }

    public Long getUserIdFromToken(String token) {
        String jwtToken = token.replace("Bearer ", "");
        return jwtUtils.extractUserId(jwtToken);
    }

    public void updateUserDetails(Long userId, String firstName, String LastName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setFirstName(firstName);
        user.setLastName(LastName);
        userRepository.save(user);
    }

    public UserProfileDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserProfileDTO userProfileDTO = new UserProfileDTO(user);
        return userProfileDTO;
    }

    public Long getUserIdFromUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getId();
    }
}
