package com.orange.toystore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.orange.toystore.api.Toy;
import com.orange.toystore.api.ToyPutRequest;
import com.orange.toystore.api.ToyResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class ToyResponseStoreApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Value("${app.url}")
	private String url;
	@Autowired
	private TestRepository repository;
	private static ObjectWriter ow;

	@BeforeAll
	public static void init(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = objectMapper.writer().withDefaultPrettyPrinter();
	}
	@Test
	@Sql(statements = "DELETE FROM toys where id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testAddNewToyEndpoint() throws Exception {
		Toy toy = new Toy();
		toy.setAge(18);
		toy.setDescription("desc");
		toy.setPrice(20);
		toy.setName("toy1");
		toy.setColor("color");
		String requestJson = ow.writeValueAsString(toy);

		mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(ow.writeValueAsString(repository.findById(1L).get())))
				.andReturn();
	}
	@Test
	@Sql(statements = "INSERT INTO toys(id, age, color, description, name, price) VALUES (1, 18, 'color', 'desc', 'name', 20)"
			, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM toys where id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateToyEndpoint() throws Exception {
		ToyPutRequest toy =
				new ToyPutRequest(1L, "nameUpdated", "desc", 20, "color", 18);

		mockMvc.perform(MockMvcRequestBuilders.put(url)
						.contentType(MediaType.APPLICATION_JSON)
						.content(ow.writeValueAsString(toy)))
				.andExpect(status().isAccepted())
				.andExpect(content().json(ow.writeValueAsString(repository.findById(1L).get())))
				.andReturn();

	}

	@Test
	@Sql(statements = "INSERT INTO toys(id, age, color, description, name, price) VALUES (1, 18, 'color', 'desc', 'name', 20)"
	, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM toys where id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testListAllToysEndpoint() throws Exception {
		List<ToyResponse> toys = new ArrayList<>();
		ToyResponse toy = new ToyResponse(1L, "name", "desc", 20, "color", 18);
		toys.add(toy);

		mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(ow.writeValueAsString(toys)))
				.andReturn();

	}


}
