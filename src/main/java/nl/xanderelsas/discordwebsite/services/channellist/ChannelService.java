package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import nl.xanderelsas.discordwebsite.model.channellist.Message;

import java.util.List;

/**
 * Used for handling a {@link Channel}, capable of fetching a list of
 * {@link Message} objects generated using data from a Discord server.
 */
public class ChannelService {
    private MessageListFactory messageListFactory;
    private Channel channel;
    private List<Message> messages;

    public ChannelService(MessageListFactory messageListFactory, Channel channel) {
        this.messageListFactory = messageListFactory;
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + this.getChannel().getId() + '"' +
                ", \"name\":\"" + this.getChannel().getName() + '"' +
                ", \"messages\":" + this.getMessages() +
                '}';
    }

    public List<Message> getMessages() {
        if (this.messages == null) {
            this.messages = this.messageListFactory.build(this.getChannel().getId());
        }

        return this.messages;
    }
}
