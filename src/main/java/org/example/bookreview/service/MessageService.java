package org.example.bookreview.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.bookreview.DTOs.MessageRequest;
import org.example.bookreview.model.Message;
import org.example.bookreview.model.User;
import org.example.bookreview.repository.MessageRepository;
import org.example.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bookreview.DTOs.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendMessage(MessageRequest messageRequest, Long senderId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new EntityNotFoundException("Sender not found"));
        User receiver = userRepository.findByUsername(messageRequest.getReceiverUsername());

        Message message = new Message();
        message.setSenderId(sender.getId());
        message.setReceiverId(receiver.getId());
        message.setContent(messageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
    }

    public List<MessageResponse> getMessagesForUser(Long userId) {
        List<Message> messages = messageRepository.findByReceiverId(userId);
        return messages.stream().map(this::convertToMessageResponse).collect(Collectors.toList());
    }

    private MessageResponse convertToMessageResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setContent(message.getContent());
        response.setTimestamp(message.getTimestamp());

        User sender = userRepository.findById(message.getSenderId())
                .orElseThrow(() -> new EntityNotFoundException("Sender not found"));
        response.setSenderName(sender.getUsername());

        return response;
    }
}
