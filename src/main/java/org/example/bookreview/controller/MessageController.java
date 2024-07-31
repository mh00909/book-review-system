package org.example.bookreview.controller;

import org.example.bookreview.DTOs.MessageRequest;
import org.example.bookreview.DTOs.MessageResponse;
import org.example.bookreview.service.MessageService;
import org.example.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling HTTP requests related to messaging between users.
 * Provides endpoints for sending and retrieving messages.
 */

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest messageRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Long senderId = userService.getUserIdFromUsername(userDetails.getUsername());
        messageService.sendMessage(messageRequest, senderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getUserMessages(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getUserIdFromUsername(userDetails.getUsername());
        List<MessageResponse> messages = messageService.getMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }
}
