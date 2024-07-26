package org.example.bookreview.controller;

import org.example.bookreview.DTOs.AuthorDTO;
import org.example.bookreview.DTOs.AuthorSummaryDTO;
import org.example.bookreview.model.Author;
import org.example.bookreview.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorSummaryDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorSummaryDTO authorRequest) {
        return authorService.createAuthor(authorRequest);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorSummaryDTO authorRequest) {
        return authorService.updateAuthor(id, authorRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
