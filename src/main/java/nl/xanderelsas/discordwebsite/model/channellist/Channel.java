package nl.xanderelsas.discordwebsite.model.channellist;

import java.util.List;

/**
 * Represents a channel containing a list of messages generated using data from a discord server.
 */
public class Channel {
    private MessageListFactory messageListFactory;
    private String id;
    private String name;
    private List<Message> messages;

    public Channel(MessageListFactory messageListFactory, String id, String name) {
        this.messageListFactory = messageListFactory;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + this.getId() + '"' +
                ", \"name\":\"" + this.getName() + '"' +
                ", \"messages\":" + this.getMessages() +
                '}';
    }

    public List<Message> getMessages() {
        if (this.messages == null) {
            this.messages = this.messageListFactory.build(this.getId());
        }

        return this.messages;
    }
}
