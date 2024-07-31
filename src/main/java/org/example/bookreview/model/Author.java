package org.example.bookreview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    @OneToMany(mappedBy = "author", fetch=FetchType.LAZY)
    @JsonManagedReference
    @Getter @Setter private List<Book> bookList = new ArrayList<>();

    public Author (){}
    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
