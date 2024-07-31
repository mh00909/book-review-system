package org.example.bookreview.DTOs;

import org.example.bookreview.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        AuthorSummaryDTO authorDTO = new AuthorSummaryDTO(book.getAuthor().getId(), book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        List<CategoryDTO> categoryDTOs = book.getCategories().stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
        List<ReviewDTO> reviewDTOs = book.getReviews().stream()
                .map(review -> new ReviewDTO(review.getId(), review.getRating(), review.getText(), review.getBook().getId(), review.getUser().getId(), review.getUser().getUsername(), review.getHelpfulCount()))
                .collect(Collectors.toList());

        return new BookDTO(book.getId(), book.getTitle(), authorDTO, categoryDTOs, reviewDTOs, book.getReaders().size(), book.getAverageRating()
        );
    }

    public static List<BookDTO> toDTOs(List<Book> books) {
        return books.stream().map(BookMapper::toDTO).collect(Collectors.toList());
    }
}
