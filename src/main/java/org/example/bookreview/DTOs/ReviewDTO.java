package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class ReviewDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private int rating;
    @Getter @Setter private String text;
    @Getter @Setter private Long bookId;
    @Getter @Setter private Long userId;
    @Getter @Setter private String username;
    @Getter @Setter private int helpfulCount;

    
    public ReviewDTO(Long id, int rating, String content, Long bookId, Long userId, String userName, int helpfulCount) {
        this.id = id;
        this.rating = rating;
        this.text = content;
        this.bookId = bookId;
        this.userId = userId;
        this.username = userName;
        this.helpfulCount = helpfulCount;
    }

}
