package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service used for handling the available Channel models, and creating
 * ChannelService objects for handling the Channel objects.
 */
@Service
public class ChannelMapService {
    @Autowired
    private MessageListFactory messageListFactory;
    @Autowired
    private ChannelMapFactory channelMapFactory;
    private Map<String, Channel> channelMap;

    public Map<String, Channel> getChannelMap() {
        if (this.channelMap == null) {
            this.channelMap = this.channelMapFactory.build();
        }

        return this.channelMap;
    }

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
