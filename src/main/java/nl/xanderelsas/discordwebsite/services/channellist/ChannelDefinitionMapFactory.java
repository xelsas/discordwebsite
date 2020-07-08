package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to build a map of {@link ChannelDefinition} objects generated using data from a Discord server.
 */
@Service
public class ChannelDefinitionMapFactory {
    /**
     * @return a map of {@link ChannelDefinition} objects generated using data from a Discord server.
     */
    public Map<String, ChannelDefinition> build() {
        Map<String, ChannelDefinition> channelMap = new LinkedHashMap<>();

        channelMap.put("1", new ChannelDefinition("1", "channel 1"));
        channelMap.put("2", new ChannelDefinition("2", "channel 2"));

        return channelMap;
    }
}
