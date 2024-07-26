package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class ReviewRequest {
    @Getter @Setter private int rating;
    @Getter @Setter private String content;
    @Getter @Setter private Long userId;
    @Getter @Setter private Long bookId;

}
