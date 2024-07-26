package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.model.Author;
import org.example.bookreview.model.Book;

import java.util.List;
import java.util.stream.Collectors;

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

    public AuthorDTO() {}
}
