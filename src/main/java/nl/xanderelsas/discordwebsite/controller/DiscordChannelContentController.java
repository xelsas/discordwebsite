package nl.xanderelsas.discordwebsite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelDefinitionMapService;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DiscordChannelContentController
{
    private ChannelDefinitionMapService channelDefinitionMapService;

    public DiscordChannelContentController(ChannelDefinitionMapService channelDefinitionMapService) {
        this.channelDefinitionMapService = channelDefinitionMapService;
    }

    @GetMapping(value = {"/","/channels"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelList() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(this.channelDefinitionMapService.getChannelMap());
    }

    @GetMapping(value = "/channel/{channelId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelContent(@PathVariable String channelId, HttpServletResponse response) throws JsonProcessingException {
        ChannelService channelService = this.channelDefinitionMapService.getChannelService(channelId);

        if (channelService == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        return (new ObjectMapper()).writeValueAsString(channelService.getChannel());
    }
}