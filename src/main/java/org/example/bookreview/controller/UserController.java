package org.example.bookreview.controller;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.DTOs.UserProfileDTO;
import org.example.bookreview.model.Book;
import org.example.bookreview.model.User;
import org.example.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for handling user-related operations.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDTO>> getAllUsers() {
        List<UserProfileDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new UserInfoResponse(authentication.getName()));
    }



    @GetMapping("/{id}")
    public UserProfileDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserProfileDTO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserProfileDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/books")
    public UserProfileDTO addReadBook(@PathVariable Long userId, @RequestBody Book book) {
        return userService.addReadBook(userId, book);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile() {
        UserProfileDTO currentUser = userService.getCurrentUser();
        UserProfileDTO userProfile = userService.getUserProfile(currentUser.getId());
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest updateRequest, @RequestHeader("Authorization") String token) {
        try {
            Long userId = userService.getUserIdFromToken(token);
            userService.updateUserDetails(userId, updateRequest.getFirstName(), updateRequest.getLastName());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details");
        }
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserProfileDTO> getUserByUsername(@PathVariable String username) {
        UserProfileDTO userDTO = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

}

/**
 * DTO for user information response.
 */
@Getter
@Setter
@AllArgsConstructor
class UserInfoResponse {
    private String username;
}

/**
 * DTO for updating user details.
 */
@Getter
@Setter
@AllArgsConstructor
class UserUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
}