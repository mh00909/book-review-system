package org.example.bookreview.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.DTOs.BookDTO;
import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.DTOs.ReviewRequest;
import org.example.bookreview.service.BookService;
import org.example.bookreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling HTTP requests related to books and their reviews.
 * Provides endpoints for CRUD operations on books and managing reviews.
 */

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody CreateBookRequest request) {
        return bookService.createBook(request);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody CreateBookRequest request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<BookDTO> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }

    @PostMapping("/{bookId}/reviews")
    public ReviewDTO addReview(@PathVariable Long bookId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReviewOrUpdate(bookId, reviewRequest);
    }

    @GetMapping("/{bookId}/reviews")
    public List<ReviewDTO> getReviewsForBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }

    public static class CreateBookRequest {
        @Getter @Setter private String title;
        @Getter @Setter private Long authorId;
        @Getter @Setter private List<Long> categoryIds;

    }


}
