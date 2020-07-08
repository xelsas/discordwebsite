package nl.xanderelsas.discordwebsite.model.channellist;

/**
 * Represents a channel definition.
 */
public class ChannelDefinition {
    private String id;
    private String name;

    public ChannelDefinition(String id, String name) {
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
