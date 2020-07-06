package nl.xanderelsas.discordwebsite.model.channellist;

/**
 * Represents a channel containing a list of messages generated using data from a Discord server.
 */
public class Channel {
    private String id;
    private String name;

    public Channel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
