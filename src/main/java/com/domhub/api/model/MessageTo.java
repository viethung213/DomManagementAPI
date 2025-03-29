package com.domhub.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "message_to")
@Getter
@Setter
@NoArgsConstructor
public class MessageTo {
    
    @EmbeddedId
    private MessageToId id;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    public MessageTo(MessageToId id) {
        this.id = id;
    }
}
