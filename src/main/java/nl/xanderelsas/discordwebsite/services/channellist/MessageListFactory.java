package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.discordobjects.Channel;
import nl.xanderelsas.discordwebsite.model.discordobjects.Message;
import nl.xanderelsas.discordwebsite.services.Config;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Used to build a list of {@link Message} objects generated using data from a Discord server.
 */
@Service
public class MessageListFactory {
    /**
     *
     * @param channelId channel id of the {@link Channel} you wish to retrieve the {@link List<Message>} for.
     * @return {@link List<Message>} for the given channelId generated using data from a discord server.
     */
    public List<Message> build(Config config, String channelId) {
        List<Message> messages = new LinkedList<Message>();

        messages.add(new Message("author_1", LocalDateTime.parse("2015-02-20T06:30:00"), "content_1"));
        messages.add(new Message("author_2", LocalDateTime.parse("2015-02-21T06:30:00"), "content_2"));

        return messages;
    }
}
