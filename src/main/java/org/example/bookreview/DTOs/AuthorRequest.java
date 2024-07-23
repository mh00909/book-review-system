package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class AuthorRequest {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
}