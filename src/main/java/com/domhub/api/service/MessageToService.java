package com.domhub.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domhub.api.model.MessageTo;
import com.domhub.api.repository.MessageToRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageToService {
    private final MessageToRepository messageToRepository;

    public List<MessageTo> findMessagesByReceiver(int receiverId) {
        return messageToRepository.findById_Receiver(receiverId);
    }

    
}
