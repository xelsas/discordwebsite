package nl.xanderelsas.discordwebsite.model.discordobjects;

/**
 * Represents a discord channel.
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
