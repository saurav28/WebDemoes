package org.saurav.springbootdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private ProjectRestController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
