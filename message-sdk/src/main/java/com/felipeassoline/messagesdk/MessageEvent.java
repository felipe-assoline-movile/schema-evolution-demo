package com.felipeassoline.messagesdk;

import java.util.UUID;

public class MessageEvent {

    private UUID id;
    private String to;
    private String text;

    public MessageEvent() {
    }

    public MessageEvent(String to, String text) {
        this.id = UUID.randomUUID();
        this.to = to;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
