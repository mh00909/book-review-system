package org.example.bookreview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private Long senderId;
    @Getter @Setter private Long receiverId;
    @Getter @Setter private String content;
    @Getter @Setter private LocalDateTime timestamp;

}
