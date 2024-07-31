package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

import org.example.bookreview.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserProfileDTO {
    @Getter @Setter private long id;
    @Getter @Setter private String username;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private List<ReviewDTO> reviews;

    public UserProfileDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.reviews = user.getReviews().stream().map(ReviewMapper::toDTO).collect(Collectors.toList());
        this.username = user.getUsername();
    }
    public UserProfileDTO(){}
}
