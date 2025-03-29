package com.domhub.api.model;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="message")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime date;

    @Column(name = "sent_by", nullable = false) // Đảm bảo là Integer
    private Integer sentBy;

    public Message(String title, String content, int sentBy) {
        this.title = title;
        this.content = content;
        this.sentBy = sentBy;
    }
}
