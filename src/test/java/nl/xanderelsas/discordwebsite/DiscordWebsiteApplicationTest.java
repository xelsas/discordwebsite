package nl.xanderelsas.discordwebsite;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl.xanderelsas.discordwebsite.model.channellist.ChannelDefinition;
import nl.xanderelsas.discordwebsite.services.channellist.ChannelDefinitionMapFactory;
import nl.xanderelsas.discordwebsite.model.channellist.Message;
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
    private ChannelDefinitionMapFactory channelDefinitionMapFactory;

    @MockBean
    private MessageListFactory messageListFactory;

    private void setupMocks() {
        Map<String, ChannelDefinition> channelMap = new LinkedHashMap<>();
        channelMap.put("channel_key_1", new ChannelDefinition("channel_key_1", "channel 1"));
        channelMap.put("2", new ChannelDefinition("2", "channel 2"));

        Mockito.when(channelDefinitionMapFactory.build()).thenReturn(channelMap);

        List<Message> messages = new LinkedList<Message>();
        messages.add(new Message("test_author", LocalDateTime.parse("2015-02-20T06:30:00"), "test_content"));
        messages.add(new Message("test_author_2", LocalDateTime.parse("2015-02-21T06:30:00"), "test_content_2"));

        Mockito.when(messageListFactory.build(Mockito.any(String.class))).thenReturn(messages);
    }

    @Test
    public void shouldReturnChannelListRoot() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"channel_key_1\":{\"id\":\"channel_key_1\",\"name\":\"channel 1\"},\"2\":{\"id\":\"2\",\"name\":\"channel 2\"}}")));
    }

    @Test
    public void shouldReturnChannelList() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channels")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"channel_key_1\":{\"id\":\"channel_key_1\",\"name\":\"channel 1\"},\"2\":{\"id\":\"2\",\"name\":\"channel 2\"}}")));
    }

    @Test
    public void shouldReturnChannelContent() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channel/channel_key_1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"channelDefinition\":{\"id\":\"channel_key_1\",\"name\":\"channel 1\"},\"messages\":[{\"author\":\"test_author\",\"timestamp\":{\"nano\":0,\"year\":2015,\"monthValue\":2,\"dayOfMonth\":20,\"hour\":6,\"minute\":30,\"second\":0,\"dayOfWeek\":\"FRIDAY\",\"dayOfYear\":51,\"month\":\"FEBRUARY\",\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"content\":\"test_content\"},{\"author\":\"test_author_2\",\"timestamp\":{\"nano\":0,\"year\":2015,\"monthValue\":2,\"dayOfMonth\":21,\"hour\":6,\"minute\":30,\"second\":0,\"dayOfWeek\":\"SATURDAY\",\"dayOfYear\":52,\"month\":\"FEBRUARY\",\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"content\":\"test_content_2\"}]}")));
    }

    @Test
    public void shouldReturnChannelList404() throws Exception {
        setupMocks();

        this.mockMvc.perform(
                get("/channel/non_existing_channel")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("")));
    }
}
