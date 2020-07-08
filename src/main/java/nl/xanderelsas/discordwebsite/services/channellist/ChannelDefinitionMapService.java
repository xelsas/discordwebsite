package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service used for handling the available {@link ChannelDefinition} models, and creating
 * {@link ChannelService} objects for handling the {@link ChannelDefinition} objects.
 */
@Service
public class ChannelDefinitionMapService {
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
     * Used to build a {@link ChannelService} object, given a channelId.
     *
     * @param channelId
     * @return {@link ChannelService} object for the {@link ChannelDefinition} with the given channelId,
     *         or null if no {@link ChannelDefinition} with the given channelId could be found
     */
    public ChannelService getChannelService(String channelId) {
        if (!this.getChannelMap().containsKey(channelId)) {
            return null;
        }

        return new ChannelService(this.messageListFactory, this.getChannelMap().get(channelId));
    }
}
