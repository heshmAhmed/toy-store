package com.orange.toystore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.orange.toystore.api.Toy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class ToyResponseStoreApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;
	@Value("${app.url}")
	private String url;
	@Autowired
	private TestRepository repository;

	private static ObjectMapper objectMapper;

	@BeforeAll
	public static void init(){
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	}

	@Test
	public void testAddNewToyEndpoint() throws Exception {
		Toy toy = new Toy();
		toy.setAge(18);
		toy.setDescription("desc");
		toy.setPrice(20);
		toy.setName("toy1");
		toy.setColor("color");
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(toy);

		mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(ow.writeValueAsString(repository.findById(1L).get())))
				.andReturn();
	}

}
