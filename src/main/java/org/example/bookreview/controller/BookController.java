package org.example.bookreview.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.bookreview.DTOs.BookDTO;
import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.model.*;
import org.example.bookreview.repository.AuthorRepository;
import org.example.bookreview.repository.BookRepository;
import org.example.bookreview.repository.CategoryRepository;
import org.example.bookreview.repository.UserRepository;
import org.example.bookreview.service.BookService;
import org.example.bookreview.service.ReviewService;
import org.example.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody CreateBookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(author);
        book.setCategories(categories);
        book = bookRepository.save(book);

        return new BookDTO(book);
    }


    @PostMapping("/{bookId}/reviews")
    public ReviewDTO addReview(@PathVariable Long bookId, @RequestBody ReviewRequest reviewRequest) {
        Book book = bookService.getBookById(bookId);
        User user = userService.getCurrentUser();

        Review review = new Review();
        review.setRating(reviewRequest.getRating());
        review.setText(reviewRequest.getContent());
        review.setBook(book);
        review.setUser(user);

        review = reviewService.createReview(review);

        bookService.createBook(book);

        return new ReviewDTO(review.getId(), review.getRating(), review.getText(),
                review.getBook().getId(), review.getUser().getId(), review.getUser().getUsername());
    }

    @GetMapping("/{bookId}/reviews")
    public List<ReviewDTO> getReviewsForBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId)
                .stream()
                .map(review -> new ReviewDTO(review.getId(), review.getRating(), review.getText(),
                        review.getBook().getId(), review.getUser().getId(), review.getUser().getUsername()))
                .collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<BookDTO> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }


    public static class CreateBookRequest {
        @Getter @Setter private String title;
        @Getter @Setter private Long authorId;
        @Getter @Setter private List<Long> categoryIds;
    }
    public class ReviewRequest {
        @Getter @Setter private int rating;
        @Getter @Setter private String content;
    }

}



