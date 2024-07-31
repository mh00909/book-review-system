package org.example.bookreview.serviceTest;

import org.example.bookreview.DTOs.UserProfileDTO;
import org.example.bookreview.model.ERole;
import org.example.bookreview.model.User;
import org.example.bookreview.repository.UserRepository;
import org.example.bookreview.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

}
