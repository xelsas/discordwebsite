package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;

/**
 * Used for creating a {@link Channel}, when given a {@link ChannelDefinition}.
 */
public class ChannelService {
    private Channel channel;
    private MessageListFactory messageListFactory;
    private ChannelDefinition channelDefinition;

    public ChannelService(MessageListFactory messageListFactory, ChannelDefinition channelDefinition) {
        this.messageListFactory = messageListFactory;
        this.channelDefinition = channelDefinition;
    }

    public MessageListFactory getMessageListFactory() {
        return messageListFactory;
    }

    public ChannelDefinition getChannelDefinition() {
        return channelDefinition;
    }

    public Channel getChannel() {
        if (this.channel == null) {
            this.channel = new Channel(
                    this.getChannelDefinition(),
                    this.getMessageListFactory().build(channelDefinition.getId())
            );
        }

        return channel;
    }
}
