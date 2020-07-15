package nl.xanderelsas.discordwebsite;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.xanderelsas.discordwebsite.model.discordobjects.Channel;
import nl.xanderelsas.discordwebsite.services.Config;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelMapFactory;
import nl.xanderelsas.discordwebsite.model.discordobjects.Message;
import nl.xanderelsas.discordwebsite.services.channellist.MessageListFactory;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscordWebsiteApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelMapFactory channelMapFactory;

    @MockBean
    private MessageListFactory messageListFactory;

    private void setupMocks() {
        Map<String, Channel> channelMap = this.getTestChannelMap();

        Mockito.when(channelMapFactory.build(Mockito.any(Config.class))).thenReturn(channelMap);

        List<Message> messages = this.getTestMessagesList();

        Mockito.when(messageListFactory.build(Mockito.any(Config.class), Mockito.any(String.class))).thenReturn(messages);
    }

    private Map<String, Channel> getTestChannelMap() {
        Map<String, Channel> channelMap = new LinkedHashMap<>();
        channelMap.put("channel_key_1", new Channel("channel_key_1", "channel 1"));
        channelMap.put("2", new Channel("2", "channel 2"));
        return channelMap;
    }

    private List<Message> getTestMessagesList() {
        List<Message> messages = new LinkedList<Message>();
        messages.add(new Message("test_author", LocalDateTime.parse("2015-02-20T06:30:00"), "test_content"));
        messages.add(new Message("test_author_2", LocalDateTime.parse("2015-02-21T06:30:00"), "test_content_2"));
        return messages;
    }

    @Test
    public void shouldReturnChannelList() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channels")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().string(
                                containsString(
                                        (new ObjectMapper()).writeValueAsString(this.getTestChannelMap())
                                )
                        )
                );
    }

    @Test
    public void shouldReturnChannel() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channels/channel_key_1")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().string(
                                containsString(
                                        (new ObjectMapper()).writeValueAsString(this.getTestChannelMap().get("channel_key_1"))
                                )
                        )
                );
    }

    @Test
    public void shouldReturnChannelMessages() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channels/channel_key_1/messages")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().string(
                                containsString(
                                        (new ObjectMapper()).writeValueAsString(this.getTestMessagesList())
                                )
                        )
                );
    }

    @Test
    public void shouldReturnChannelList404() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channels/non_existing_channel")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("")));
    }
}
