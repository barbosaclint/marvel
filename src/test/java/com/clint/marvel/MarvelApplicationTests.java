package com.clint.marvel;

import com.clint.marvel.service.MarvelCharacterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MarvelApplicationTests {

	@Mock
	private  MarvelCharacterService marvelCharacterService;

	@Autowired
	RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

}
