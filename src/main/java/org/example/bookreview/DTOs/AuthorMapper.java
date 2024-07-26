package org.example.bookreview.DTOs;

import org.example.bookreview.model.Author;

import java.util.stream.Collectors;

public class AuthorMapper {
    public static AuthorDTO toAuthorDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName(),
                author.getBookList().stream().map(BookMapper::toDTO).collect(Collectors.toList()));
    }
    public static AuthorSummaryDTO toSummaryDTO(Author author) {
        return new AuthorSummaryDTO(author.getId(), author.getFirstName(), author.getLastName());
    }

    public static AuthorDTO toDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName(), author.getBookList().stream().map(BookMapper::toDTO).collect(Collectors.toList()));
    }
}
