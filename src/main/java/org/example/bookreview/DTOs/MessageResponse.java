package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class MessageResponse {
    @Getter @Setter private Long id;
    @Getter @Setter private String senderName;
    @Getter @Setter private String content;
    @Getter @Setter private LocalDateTime timestamp;
}
