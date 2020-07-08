package nl.xanderelsas.discordwebsite.model.channellist;

import java.util.List;

/**
 * Represents a channel containing a list of messages generated using data from a Discord server.
 */
public class Channel {
    private ChannelDefinition channelDefinition;
    private List<Message> messages;

    public Channel(ChannelDefinition channelDefinition, List<Message> messages) {
        this.channelDefinition = channelDefinition;
        this.messages = messages;
    }

    public ChannelDefinition getChannelDefinition() {
        return channelDefinition;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
