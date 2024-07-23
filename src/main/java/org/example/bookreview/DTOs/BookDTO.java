package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.model.Book;
import org.example.bookreview.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private AuthorDTO author;
    @Getter @Setter private List<CategoryDTO> categories;
    @Getter @Setter private List<ReviewDTO> reviews;
    @Getter @Setter private double averageRating;


    public BookDTO(Long id, String title, AuthorDTO author, List<CategoryDTO> categories, List<ReviewDTO> reviews, double averageRating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.averageRating = averageRating;
        this.reviews = reviews;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = new AuthorDTO(book.getAuthor());
        this.categories = new ArrayList<CategoryDTO>();

        for(Category category : book.getCategories()) {
            CategoryDTO c = new CategoryDTO(category.getId(), category.getName());
            categories.add(c);
        }

        this.averageRating = book.getAverageRating();
    }
}
