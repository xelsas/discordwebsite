package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to build a map of {@link Channel} objects generated using data from a Discord server.
 */
@Service
public class ChannelMapFactory {
    /**
     * @return a map of {@link Channel} objects generated using data from a Discord server.
     */
    public Map<String, Channel> build() {
        Map<String, Channel> channelMap = new LinkedHashMap<>();

        channelMap.put("1", new Channel("1", "channel 1"));
        channelMap.put("2", new Channel("2", "channel 2"));

        return channelMap;
    }
}
