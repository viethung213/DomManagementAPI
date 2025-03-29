package com.domhub.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.domhub.api.dto.request.MessageRequest;
import com.domhub.api.dto.request.MessageToRequest;
import com.domhub.api.model.Message;
import com.domhub.api.model.MessageTo;
import com.domhub.api.model.MessageToId;
import com.domhub.api.model.Notification;
import com.domhub.api.repository.MessageRepository;
import com.domhub.api.repository.MessageToRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageToRepository messageToRepository;

    public String addMessage(MessageRequest request){
        try {
            Message message = messageRepository.save(new Message(
            request.getTitle(), 
            request.getContent(), 
            request.getSent_by()
        ));

        List<MessageTo> messageTos = request.getReceiver().stream()
            .map(receiverId -> new MessageTo(new MessageToId(message.getId(), receiverId)))
            .collect(Collectors.toList());

        messageToRepository.saveAll(messageTos);
            return "Notification created successfully";
        } catch (Exception e) {
            return "Notification creation failed: " + e.getMessage();
        }
    }

    public List<Message> getAllMessage(){
        return messageRepository.findAll();
    }

    public Message findMessageById(Integer id){
        return messageRepository.findById(id).orElse(null);
    }
    
    public List<Message> findMessagesBySendBy(Integer id){
        return messageRepository.findBySentBy(id);
    }
    
    public List<Message> getAllRecipientMessages(Integer id){
        return messageToRepository.findById_Receiver(id).stream()
            .map(messageTo -> messageRepository.findById(messageTo.getId().getMessageId()).orElse(null))
            .filter(Objects::nonNull) // Lọc bỏ các phần tử null nếu có
            .collect(Collectors.toList());
    }

    public String setRead(MessageToRequest messageRequest){
        MessageToId messageToId = new MessageToId(messageRequest.getMessageId(), messageRequest.getReceiver());
        Optional<MessageTo> optionalMessageTo = messageToRepository.findById(messageToId);

        if (optionalMessageTo.isPresent()) {
            MessageTo messageTo = optionalMessageTo.get();
            messageTo.setRead(true);
            messageToRepository.save(messageTo);
            return "Message marked as read.";
        } else {
            return "Message not found.";
        }
    }

    public Message getMessageID(int id){
        Message message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
        return message;
    }
}
