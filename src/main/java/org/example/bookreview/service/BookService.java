package org.example.bookreview.service;

import org.example.bookreview.DTOs.BookDTO;
import org.example.bookreview.DTOs.BookMapper;
import org.example.bookreview.model.Book;
import org.example.bookreview.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return BookMapper.toDTOs(books);
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? book : null;
    }

    public BookDTO createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDTO> getBooksByCategory(Long categoryId) {
        List<Book> books = bookRepository.findByCategories_Id(categoryId);
        return BookMapper.toDTOs(books);
    }
}

