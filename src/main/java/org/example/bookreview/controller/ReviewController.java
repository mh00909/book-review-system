package org.example.bookreview.controller;

import org.example.bookreview.DTOs.ReviewDTO;
import org.example.bookreview.DTOs.ReviewRequest;
import org.example.bookreview.model.Review;
import org.example.bookreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewRequest review) {
        return reviewService.addReview(review.getBookId(), review);
    }


    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
