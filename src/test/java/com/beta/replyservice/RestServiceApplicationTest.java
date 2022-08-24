package com.beta.replyservice;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void givenEmptyMessage_whenCallingReplyV1_thenReturnBadRequest() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/reply", String.class)
		).contains("{\"message\":\"Message is empty\"}");
	}

	@Test
	public void givenValidMessage_whenCallingReplyV1_thenReturnSameString() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/reply/test", String.class)
		).contains("{\"data\":\"test\"}");
	}

	@Test
	public void givenInvalidMessage_whenCallingReplyV2_thenReturnBadRequest() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/v2/reply/2-kbzw9ru", String.class)
		).contains("{\"message\":\"Invalid input\"}");
	}

	@Test
	public void givenMessageWithInvalidOperation_whenCallingReplyV2_thenReturnBadRequest() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/v2/reply/13-kbzw9ru", String.class)
		).contains("{\"message\":\"Operation not found: 3\"}");
	}

	@Test
	public void givenValidMessage_whenCallingReplyV2_thenReturnDoubleReversedString() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/v2/reply/11-kbzw9ru", String.class)
		).contains("{\"data\":\"kbzw9ru\"}");
	}

	@Test
	public void givenValidMessage_whenCallingReplyV2_thenReturnDoubleHashedString() {
		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/v2/reply/22-kbzw9ru", String.class)
		).contains("{\"data\":\"e8501e64cf0a9fa45e3c25aa9e77ffd5\"}");
	}
}
