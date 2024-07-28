package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class BookSummaryDTO {

    @Getter @Setter private long id;
    @Getter @Setter private String title;

    public BookSummaryDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
