package org.example.bookreview.DTOs;

import org.example.bookreview.model.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review) {
       return new ReviewDTO(review.getId(), review.getRating(), review.getText(), review.getBook().getId(), review.getUser().getId(), review.getUser().getUsername(), review.getHelpfulCount());
    }
    public static List<ReviewDTO> toDTOs(List<Review> reviews) {
        return reviews.stream().map(ReviewMapper::toDTO).collect(Collectors.toList());
    }
}
