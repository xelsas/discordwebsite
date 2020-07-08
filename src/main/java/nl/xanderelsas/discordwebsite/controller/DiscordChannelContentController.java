package nl.xanderelsas.discordwebsite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.xanderelsas.discordwebsite.model.channellist.Channel;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DiscordChannelContentController
{
    private ChannelService channelService;

    public DiscordChannelContentController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping(value = {"/","/channels"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelList() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(this.channelService.getChannelMap());
    }

    @GetMapping(value = "/channel/{channelId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelContent(@PathVariable String channelId, HttpServletResponse response) throws JsonProcessingException {
        Channel channel = this.channelService.getChannel(channelId);

        if (channel == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        return (new ObjectMapper()).writeValueAsString(channel);
    }
}