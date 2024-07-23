package org.example.bookreview.repository;

import org.example.bookreview.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategories_Id(Long categoryId);
}
