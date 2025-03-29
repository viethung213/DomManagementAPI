package com.domhub.api.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageToId implements Serializable {
    
    private int messageId;
    private int receiver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageToId that = (MessageToId) o;
        return messageId == that.messageId && receiver == that.receiver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, receiver);
    }
}
