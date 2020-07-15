package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.discordobjects.Channel;
import nl.xanderelsas.discordwebsite.services.Config;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to build a map of {@link Channel} objects generated using data from a Discord server.
 */
@Service
public class ChannelMapFactory {
    /**
     *
     * @param config
     * @return a map of {@link Channel} objects generated using data from a Discord server.
     */
    public Map<String, Channel> build(Config config) {
        Map<String, Channel> channelMap = new LinkedHashMap<>();

        channelMap.put("1", new Channel("1", "channel 1"));
        channelMap.put("2", new Channel("2", "channel 2"));

        return channelMap;
    }
}
