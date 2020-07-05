package nl.xanderelsas.discordwebsite.model.channellist;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to build a list of channels generated using data from a discord server.
 */
@Service
public class ChannelListFactory {
    public Map<String, Channel> build(MessageListFactory messageListFactory) {
        Map<String, Channel> channelMap = new LinkedHashMap<>();

        channelMap.put("1", new Channel(messageListFactory, "1", "channel 1"));
        channelMap.put("2", new Channel(messageListFactory, "2", "channel 2"));

        return channelMap;
    }
}
