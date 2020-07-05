package nl.xanderelsas.discordwebsite.model.channellist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Represents a list of channels generated using data from a discord server.
 */
@Service
public class ChannelList {
    @Autowired
    private ChannelListFactory channelListFactory;
    @Autowired
    private MessageListFactory messageListFactory;

    private Map<String, Channel> channelMap;

    public Map<String, Channel> getChannels() {
        if (this.channelMap == null) {
            this.channelMap = this.channelListFactory.build(messageListFactory);
        }

        return this.channelMap;
    }

    public String toString() {
        String json = "[";
        for (Map.Entry<String,Channel> entry : (this.getChannels().entrySet())) {
            json += "{" +
                    "\"id\":\"" + entry.getValue().getId() + '"' +
                    ", \"name\":\"" + entry.getValue().getName() + '"' +
                    '}';
        }
        json += "]";

        return json;
    }
}
