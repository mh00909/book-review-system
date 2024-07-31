package org.example.bookreview.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.DTOs.ReviewMapper;
import org.example.bookreview.model.Book;
import org.example.bookreview.model.Review;
import org.example.bookreview.model.User;
import org.example.bookreview.repository.BookRepository;
import org.example.bookreview.repository.ReviewRepository;
import org.example.bookreview.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bookreview.DTOs.ReviewRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewDTO> getReviewsByBookId(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviews.stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO addReviewOrUpdate(Long bookId, ReviewRequest reviewRequest) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Review review = reviewRepository.findByBookIdAndUserId(bookId, user.getId())
                .orElse(new Review());

        review.setBook(book);
        review.setUser(user);
        review.setRating(reviewRequest.getRating());
        review.setText(reviewRequest.getContent());

        Review savedReview = reviewRepository.save(review);
        logger.debug("Review saved: {}", savedReview);
        return ReviewMapper.toDTO(savedReview);
    }


    public ReviewDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return ReviewMapper.toDTO(review);
    }

    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("Review not found");
        }
        reviewRepository.deleteById(reviewId);
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> new ReviewDTO(review.getId(), review.getRating(), review.getText(), review.getBook().getId(), review.getUser().getId(), review.getUser().getUsername(), review.getHelpfulCount()))
                .collect(Collectors.toList());
    }

    public void markReviewAsHelpful(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        if (!review.getHelpfulUserIds().contains(userId)) {
            review.setHelpfulCount(review.getHelpfulCount() + 1);
            review.getHelpfulUserIds().add(userId);
            reviewRepository.save(review);
        } else {
            throw new IllegalStateException("User has already marked this review as helpful");
        }
    }

    public void unmarkReviewAsHelpful(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        if (review.getHelpfulUserIds().contains(userId)) {
            review.setHelpfulCount(review.getHelpfulCount() - 1);
            review.getHelpfulUserIds().remove(userId);
            reviewRepository.save(review);
        } else {
            throw new IllegalStateException("User has not marked this review as helpful");
        }
    }



}
