package com.clint.marvel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MarvelApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

}
