package nl.xanderelsas.discordwebsite.model.discordobjects;

import java.time.LocalDateTime;

/**
 * Represents a message.
 */
public class Message {
    private String author;
    private LocalDateTime timestamp;
    private String content;

    public Message(String author, LocalDateTime timestamp, String content) {
        this.author = author;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
}
