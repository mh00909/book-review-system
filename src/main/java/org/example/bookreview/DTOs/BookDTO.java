package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


public class BookDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private AuthorSummaryDTO author;
    @Getter @Setter private List<CategoryDTO> categories;
    @Getter @Setter private List<ReviewDTO> reviews;
    @Getter @Setter private int numberOfReaders;
    @Getter @Setter private double averageRating;


    public BookDTO(Long id, String title, AuthorSummaryDTO author, List<CategoryDTO> categories,
                   List<ReviewDTO> reviews, int numberOfReaders, double averageRating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.reviews = reviews;
        this.numberOfReaders = numberOfReaders;
        this.averageRating = averageRating;
    }



}
