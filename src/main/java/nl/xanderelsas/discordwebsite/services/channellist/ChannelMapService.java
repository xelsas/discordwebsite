package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service used for handling the available {@link Channel} models, and creating
 * {@link ChannelService} objects for handling the {@link Channel} objects.
 */
@Service
public class ChannelMapService {
    @Autowired
    private MessageListFactory messageListFactory;
    @Autowired
    private ChannelMapFactory channelMapFactory;
    private Map<String, Channel> channelMap;

    /**
     * @return a {@link Map} of all available channels in the Discord server, represented by {@link Channel} objects.
     */
    public Map<String, Channel> getChannelMap() {
        if (this.channelMap == null) {
            this.channelMap = this.channelMapFactory.build();
        }

        return this.channelMap;
    }

    /**
     * Used to build a {@link ChannelService} object, given a channelId.
     *
     * @param channelId
     * @return {@link ChannelService} object for the {@link Channel} with the given channelId,
     *         or null if no {@link Channel} with the given channelId could be found
     */
    public ChannelService getChannelService(String channelId) {
        if (!this.getChannelMap().containsKey(channelId)) {
            return null;
        }

        return new ChannelService(this.messageListFactory, this.getChannelMap().get(channelId));
    }

    public String toString() {
        String json = "[";
        for (Map.Entry<String, Channel> entry : (this.getChannelMap().entrySet())) {
            json += "{" +
                    "\"id\":\"" + entry.getValue().getId() + '"' +
                    ", \"name\":\"" + entry.getValue().getName() + '"' +
                    '}';
        }
        json += "]";

        return json;
    }
}
