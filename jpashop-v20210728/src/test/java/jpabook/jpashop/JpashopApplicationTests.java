package jpabook.jpashop;

import com.fasterxml.jackson.databind.ObjectMapper;
import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JpashopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateUser() throws Exception {
		// CreateUserRequest 객체를 생성합니다.
		Book newBook = new Book();
		newBook.setName("cho");
		newBook.setPrice(1000);
		newBook.setStockQuantity(10);
		newBook.setAuthor("조동찬");
		newBook.setIsbn("1");
		// "/api/users/create"에 대한 POST 요청 모의
		mockMvc.perform(MockMvcRequestBuilders.post("/items/new")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newBook)))
				// HTTP 상태코드 200인지 확인
				.andExpect(MockMvcResultMatchers.status().isOk())
				// Content-Type이 "application/json"인지 확인
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				// JSON 응답에서 필드 값 확인
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("cho"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
	}

}
