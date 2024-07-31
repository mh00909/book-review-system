package org.example.bookreview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.DTOs.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter private Long id;

    @Getter @Setter private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    @Getter @Setter private Author author;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true,  fetch=FetchType.LAZY)
    @JsonIgnore
    @Getter @Setter private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "readBooks")
    @Getter @Setter private List<User> readers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Getter @Setter private List<Category> categories = new ArrayList<>();

    @Getter @Setter private double averageRating;



    public void calculateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0.0;
        } else {
            double sum = reviews.stream().mapToInt(Review::getRating).sum();
            averageRating = sum / reviews.size();
        }
    }
}
