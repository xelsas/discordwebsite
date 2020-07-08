package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;
import nl.xanderelsas.discordwebsite.model.channellist.Message;

/**
 * Used for handling a {@link ChannelDefinition}, capable of fetching a list of
 * {@link Message} objects generated using data from a Discord server.
 */
public class ChannelService {
    private Channel channel;
    private final MessageListFactory messageListFactory;
    private final ChannelDefinition channelDefinition;

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
