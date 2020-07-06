package nl.xanderelsas.discordwebsite.controller;

import nl.xanderelsas.discordwebsite.services.channellist.ChannelMapService;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DiscordChannelContentController
{
    private ChannelMapService channelMapService;

    public DiscordChannelContentController(ChannelMapService channelMapService) {
        this.channelMapService = channelMapService;
    }

    @GetMapping(value = {"/","/channel"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelList() {

        return this.channelMapService.toString();
    }

    @GetMapping(value = "/channel/{channelId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelContent(@PathVariable String channelId, HttpServletResponse response) {
        ChannelService channelService = this.channelMapService.getChannelService(channelId);

        if (channelService == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        return channelService.toString();
    }
}