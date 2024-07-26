package org.example.bookreview.service;

import org.example.bookreview.DTOs.BookDTO;
import org.example.bookreview.DTOs.BookMapper;
import org.example.bookreview.controller.BookController;
import org.example.bookreview.model.Author;
import org.example.bookreview.model.Book;
import org.example.bookreview.model.Category;
import org.example.bookreview.repository.AuthorRepository;
import org.example.bookreview.repository.BookRepository;
import org.example.bookreview.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.DTOs.AuthorDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toDTO).toList();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return BookMapper.toDTO(book);
    }

    public BookDTO createBook(BookController.CreateBookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(author);
        book.setCategories(categories);
        book = bookRepository.save(book);

        return BookMapper.toDTO(book);
    }

    public BookDTO updateBook(Long id, BookController.CreateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        book.setTitle(request.getTitle());
        book.setAuthor(author);
        book.setCategories(categories);
        book = bookRepository.save(book);

        return BookMapper.toDTO(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public List<BookDTO> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategories_Id(categoryId).stream()
                .map(BookMapper::toDTO)
                .toList();
    }
}

