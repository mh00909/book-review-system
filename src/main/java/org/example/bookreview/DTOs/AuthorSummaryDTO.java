package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class AuthorSummaryDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    public AuthorSummaryDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
