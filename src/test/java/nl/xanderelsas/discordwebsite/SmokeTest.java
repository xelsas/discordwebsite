package nl.xanderelsas.discordwebsite;

import nl.xanderelsas.discordwebsite.controller.DiscordController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

	@Autowired
	private DiscordController controller;

	@Test
	public void contexLoads() throws Exception {
		Assertions.assertThat(controller).isNotNull();
	}

}
