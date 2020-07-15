package nl.xanderelsas.discordwebsite.services.channellist;

import nl.xanderelsas.discordwebsite.model.discordobjects.Channel;
import nl.xanderelsas.discordwebsite.model.discordobjects.Message;
import nl.xanderelsas.discordwebsite.services.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service used for handling the available {@link Channel} models, and creating
 * {@link List<Message>} from {@link Channel} objects.
 */
@Service
public class ChannelService {
    @Autowired
    private Config config;
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
            this.channelMap = this.channelMapFactory.build(this.config);
        }

        return this.channelMap;
    }

    /**
     * Used to build a {@link List<Message>} object, given a channelId.
     *
     * @param channelId channel id of the {@link Channel} you wish to retrieve the {@link List<Message>} for.
     * @return {@link List<Message>} object for the {@link Channel} with the given channelId,
     *         or null if no {@link Channel} with the given channelId could be found
     */
    public List<Message> getMessages(String channelId) {
        Channel channel = this.getChannelMap().get(channelId);

        if (channel == null) {
            return null;
        }

        return this.messageListFactory.build(this.config, channel.getId());
    }
}
