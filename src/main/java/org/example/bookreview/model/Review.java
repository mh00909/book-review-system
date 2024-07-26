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

    public Review(){}
    public Review(Long id, String text, int rating, User user, Book book) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.user = user;
        this.book = book;
    }
}
