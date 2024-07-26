package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class BookSummaryDTO { // to avoid cyclic dependency

    @Getter @Setter private long id;
    @Getter @Setter private String title;

    public BookSummaryDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
