package org.example.bookreview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {
    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter private int helpfulCount = 0;

    @ElementCollection @Getter @Setter private List<Long> helpfulUserIds = new ArrayList<>();
    @Getter @Setter private String text;
    @Getter @Setter private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @Getter @Setter private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
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
