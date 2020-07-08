package nl.xanderelsas.discordwebsite.model.channellist;

import com.fasterxml.jackson.core.JsonProcessingException;

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

    public String getJSONString() throws JsonProcessingException {
        return "{" +
                "\"author\":\"" + this.getAuthor() + '"' +
                ", \"timestamp\":\"" + this.getTimestamp().toString() + '"' +
                ", \"content\":\"" + this.getContent() + '"' +
                '}';
    }
}
