package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AuthorDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private List<BookDTO> bookList;

    public AuthorDTO(Long id, String firstName, String lastName, List<BookDTO> bookList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = bookList;
    }

}
