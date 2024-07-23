package org.example.bookreview.config;

import org.example.bookreview.model.*;
import org.example.bookreview.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitialization {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;
/*
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // users
            User user1 = new User();
            user1.setUsername("john");
            user1.setFirstName("John");
            user1.setLastName("Smith");
            user1.setEmail("john@gmail.com");
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setRole("ROLE_USER");
            userRepository.save(user1);

            User admin = new User();
            admin.setUsername("admin");
            admin.setFirstName("Admin");
            admin.setLastName("Istrator");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);

            // authors
            Author authorDost = new Author();
            authorDost.setFirstName("Fyodor");
            authorDost.setLastName("Dostoevsky");
            authorRepository.save(authorDost);

            Author authorAusten = new Author();
            authorAusten.setFirstName("Jane");
            authorAusten.setLastName("Austen");
            authorRepository.save(authorAusten);

            // categories
            Category categoryFiction = new Category();
            categoryFiction.setName("Literary fiction");
            categoryRepository.save(categoryFiction);

            Category categoryClassic = new Category();
            categoryClassic.setName("Classic");
            categoryRepository.save(categoryClassic);

            Category categoryRomance = new Category();
            categoryRomance.setName("Romance");
            categoryRepository.save(categoryRomance);

            // books
            Book bookCrime = new Book();
            bookCrime.setTitle("Crime and Punishment");
            bookCrime.setAuthor(authorDost);
            bookCrime.getCategories().add(categoryFiction);
            bookRepository.save(bookCrime);

            Book bookPride = new Book();
            bookPride.setTitle("Pride and Prejudice");
            bookPride.setAuthor(authorAusten);
            bookPride.getCategories().add(categoryClassic);
            bookPride.getCategories().add(categoryRomance);
            bookRepository.save(bookPride);

            // reviews
            Review review = new Review();
            review.setText("My favourite book");
            review.setUser(user1);
            review.setRating(9);
            review.setBook(bookCrime);

            // adding books to users
            user1.getReadBooks().add(bookCrime);
            user1.getReadBooks().add(bookPride);
            userRepository.save(user1);
        };
    }


 */

}
