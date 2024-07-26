package org.example.bookreview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @ManyToMany(mappedBy = "categories",  fetch=FetchType.LAZY)
    @JsonIgnore
    @Getter @Setter private List<Book> books = new ArrayList<>();

}
