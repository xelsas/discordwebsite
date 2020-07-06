package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Used to build a list of messages containing generated using data from a discord server.
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
