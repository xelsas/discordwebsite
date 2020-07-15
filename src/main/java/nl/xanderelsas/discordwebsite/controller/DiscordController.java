package nl.xanderelsas.discordwebsite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.xanderelsas.discordwebsite.model.discordobjects.Channel;
import nl.xanderelsas.discordwebsite.model.discordobjects.Message;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class DiscordController
{
    private ChannelService channelService;

    public DiscordController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping(value = {"/channels"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelList() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(channelService.getChannelMap());
    }

    @GetMapping(value = "/channels/{channelId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channel(@PathVariable String channelId, HttpServletResponse response) throws JsonProcessingException {
        Channel channel = channelService.getChannelMap().get(channelId);

        if (channel == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        return (new ObjectMapper()).writeValueAsString(channel);
    }

    @GetMapping(value = "/channels/{channelId}/messages", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelMessages(@PathVariable String channelId, HttpServletResponse response) throws JsonProcessingException {
        List<Message> messageList = channelService.getMessages(channelId);

        if (messageList == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        return (new ObjectMapper()).writeValueAsString(messageList);
    }
}