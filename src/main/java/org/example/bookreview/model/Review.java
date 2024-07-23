package org.example.bookreview.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Review {
    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String text;
    @Getter @Setter private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @Getter @Setter private Book book;

    @PrePersist
    @PreUpdate
    public void updateBookRating() {
        book.calculateAverageRating();
    }
}
