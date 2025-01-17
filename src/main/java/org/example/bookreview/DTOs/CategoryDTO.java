package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class CategoryDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}