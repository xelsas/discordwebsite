package nl.xanderelsas.discordwebsite.services;

import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Handles general application configuration.
 */
@Configuration
public class Config {
    private String token;

    public Config() throws Exception {
        this.loadFromConfigFile();
    }

    private String getConfigFile() {
        return "config.yml";
    }

    /**
     * Reads the configurable values from a config file.
     *
     * @throws Exception Throws an exception when the config file can not be read correctly.
     */
    private void loadFromConfigFile() throws Exception {
        Yaml yaml = new Yaml();

        InputStream is = new FileInputStream(new File(this.getConfigFile()));

        Map<String, Object> result = yaml.load(is);

        String token = (String) result.get("token");
        if (token == null || token.isEmpty()) {
            throw new Exception("Discord bot token not set!");
        }
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
