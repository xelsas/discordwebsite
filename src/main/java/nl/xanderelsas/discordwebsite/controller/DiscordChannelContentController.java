package nl.xanderelsas.discordwebsite.controller;

import nl.xanderelsas.discordwebsite.model.channellist.ChannelList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DiscordChannelContentController
{
    private ChannelList channelList;

    public DiscordChannelContentController(ChannelList channelList) {
        this.channelList = channelList;
    }

    @GetMapping(value = {"/","/channel"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelList() {

        return this.channelList.toString();
    }

    @GetMapping(value = "/channel/{channelId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String channelContent(@PathVariable String channelId, HttpServletResponse response) {
        if (!this.channelList.getChannels().containsKey(channelId)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }


        return this.channelList.getChannels().get(channelId).toString();
    }
}