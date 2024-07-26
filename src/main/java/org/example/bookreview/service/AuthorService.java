package org.example.bookreview.service;

import org.example.bookreview.DTOs.AuthorDTO;
import org.example.bookreview.DTOs.AuthorMapper;
import org.example.bookreview.DTOs.AuthorSummaryDTO;
import org.example.bookreview.model.Author;
import org.example.bookreview.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorSummaryDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            return AuthorMapper.toDTO(authorOpt.get());
        } else {
            throw new RuntimeException("Author not found");
        }
    }

    public AuthorDTO createAuthor(AuthorSummaryDTO authorRequest) {
        Author author = new Author();
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.toDTO(savedAuthor);
    }

    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Author not found");
        }
    }
    public AuthorDTO updateAuthor(Long id, AuthorSummaryDTO authorRequest) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            author.setFirstName(authorRequest.getFirstName());
            author.setLastName(authorRequest.getLastName());
            Author updatedAuthor = authorRepository.save(author);
            return AuthorMapper.toDTO(updatedAuthor);
        } else {
            throw new RuntimeException("Author not found");
        }
    }
}
