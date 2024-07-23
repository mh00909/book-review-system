package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class ReviewDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private int rating;
    @Getter @Setter private String content;
    @Getter @Setter private Long bookId;
    @Getter @Setter private Long userId;
    @Getter @Setter private String userName;

    public ReviewDTO() {}

    public ReviewDTO(Long id, int rating, String content, Long bookId, Long userId, String userName) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.bookId = bookId;
        this.userId = userId;
        this.userName = userName;
    }
}
