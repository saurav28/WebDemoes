package org.saurav.springbootdemo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Greetings from the Spring Demo application!")));
	}

	@Test
	@WithMockUser(value = "saurav")
	public void getProjectContentType() throws Exception {
		this.mockMvc.perform(get("/project")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@WithMockUser(value = "saurav")
	public void getProjectContent() throws Exception {
		this.mockMvc.perform(get("/project")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("[\n" + 
						"    {\n" + 
						"        \"id\": 1,\n" + 
						"        \"name\": \"Jackey\",\n" + 
						"        \"description\": \"Haha\"\n" + 
						"    },\n" + 
						"    {\n" + 
						"        \"id\": 2,\n" + 
						"        \"name\": \"Chloe\",\n" + 
						"        \"description\": null\n" + 
						"    },\n" + 
						"    {\n" + 
						"        \"id\": 3,\n" + 
						"        \"name\": \"Kim\",\n" + 
						"        \"description\": null\n" + 
						"    },\n" + 
						"    {\n" + 
						"        \"id\": 4,\n" + 
						"        \"name\": \"David\",\n" + 
						"        \"description\": null\n" + 
						"    },\n" + 
						"    {\n" + 
						"        \"id\": 5,\n" + 
						"        \"name\": \"Michelle\",\n" + 
						"        \"description\": null\n" + 
						"    }\n" + 
						"]"));
	}

}
