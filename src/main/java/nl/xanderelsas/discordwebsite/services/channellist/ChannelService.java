package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service used for handling the available {@link ChannelDefinition} models, and creating
 * {@link Channel} objects from {@link ChannelDefinition} objects.
 */
@Service
public class ChannelService {
    @Autowired
    private MessageListFactory messageListFactory;
    @Autowired
    private ChannelDefinitionMapFactory channelDefinitionMapFactory;
    private Map<String, ChannelDefinition> channelMap;

    /**
     * @return a {@link Map} of all available channels in the Discord server, represented by {@link ChannelDefinition} objects.
     */
    public Map<String, ChannelDefinition> getChannelMap() {
        if (this.channelMap == null) {
            this.channelMap = this.channelDefinitionMapFactory.build();
        }

        return this.channelMap;
    }

    /**
     * Used to build a {@link Channel} object, given a channelId.
     *
     * @param channelId channel id of the {@link ChannelDefinition} you wish to build a {@link Channel} object for.
     * @return {@link Channel} object for the {@link ChannelDefinition} with the given channelId,
     *         or null if no {@link ChannelDefinition} with the given channelId could be found
     */
    public Channel getChannel(String channelId) {
        ChannelDefinition channelDefinition = this.getChannelMap().get(channelId);

        if (channelDefinition == null) {
            return null;
        }

        return new Channel(
                channelDefinition,
                this.messageListFactory.build(channelDefinition.getId())
        );
    }
}
