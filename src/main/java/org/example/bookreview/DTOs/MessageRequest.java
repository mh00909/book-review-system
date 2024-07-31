package org.example.bookreview.DTOs;

import lombok.Getter;
import lombok.Setter;

public class MessageRequest {
    @Getter @Setter private String receiverUsername;
    @Getter @Setter private String content;
}
