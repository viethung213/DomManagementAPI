package com.domhub.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domhub.api.dto.request.MessageRequest;
import com.domhub.api.dto.request.MessageToRequest;
import com.domhub.api.dto.request.NotificationRequest;
import com.domhub.api.model.Message;
import com.domhub.api.service.MessageService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;



    @GetMapping("/findAll")// lấy tất cả các tin nhắn 
    public List<Message> getAllMessage() {
        return messageService.getAllMessage();
    }

    @GetMapping("/findid/{id}")// tấy tin nhắn theo id
    public Message getMessageID(@PathVariable  int  id) {
        return messageService.getMessageID(id);
    }
    

    @GetMapping("/send/{receiver}")// lấy tất cả tin nhắn do 1 người gửi 
    public List<Message> getSendMessages(@PathVariable int receiver) {
        return messageService.findMessagesBySendBy(receiver);
    }
    
    @GetMapping("/{receiver}")// lấy tất cả các tin nhắn của 1 người nhận đã nhận 
    public List<Message> getRecipientMessages(@PathVariable int receiver) {
        return messageService.getAllRecipientMessages(receiver);
    }

    @PostMapping("/isread")//set tin nhắn đã đọc 
    public ResponseEntity<String> createNotification(@RequestBody MessageToRequest request) {
        String result = messageService.setRead(request);

        if (!result.contains("read")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")//thêm tin nhắn 
    public ResponseEntity<String> postMethodName(@RequestBody MessageRequest request) {
        String result = messageService.addMessage(request);

        if(!result.contains("successfully")){
            return ResponseEntity.badRequest().body(result);
        }
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/receiver/{send}")// lấy danh sách người nhận của 1 người gửi 
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
}
