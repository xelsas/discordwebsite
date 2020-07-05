package nl.xanderelsas.discordwebsite.model.channellist;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Used to build a channel containing a list of messages generated using data from a discord server.
 */
@Service
public class MessageListFactory {
    public List<Message> build(String channelId) {
        List<Message> messages = new LinkedList<Message>();

        messages.add(new Message("author_1", LocalDateTime.parse("2015-02-20T06:30:00"), "content_1"));
        messages.add(new Message("author_2", LocalDateTime.parse("2015-02-21T06:30:00"), "content_2"));

        return messages;
    }
}
