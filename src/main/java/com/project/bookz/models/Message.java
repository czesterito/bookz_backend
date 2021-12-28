package com.project.bookz.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MESSAGE")
@Table(name = "`MESSAGE`")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", updatable = false)
    private Integer messageId;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "time")
    private Timestamp time;

    public Message() {
    }

    public Message(String content, Timestamp time) {
        this.content = content;
        this.time = time;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
