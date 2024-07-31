package org.example.bookreview.controller;

import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.service.ReviewService;
import org.example.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling HTTP requests related to book reviews.
 * Provides endpoints for CRUD operations on reviews and marking them as helpful.
 */

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    @PostMapping("/{id}/helpful")
    public ResponseEntity<Void> markReviewAsHelpful(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserIdFromToken(token);
        reviewService.markReviewAsHelpful(id, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unhelpful")
    public ResponseEntity<Void> unmarkReviewAsHelpful(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserIdFromToken(token);
        reviewService.unmarkReviewAsHelpful(id, userId);
        return ResponseEntity.ok().build();
    }

}
