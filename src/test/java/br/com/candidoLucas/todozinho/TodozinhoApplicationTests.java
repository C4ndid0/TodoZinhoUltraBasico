package br.com.candidoLucas.todozinho;

import br.com.candidoLucas.todozinho.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodozinhoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void CreateTodoSuccess() {
		var todo = new Todo("Todo 1", "desc todo 1", false , 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].status").isEqualTo(todo.getStatus())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());

	}

	@Test
	void CreateTodoFailure() {

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("","", false,0))
				.exchange()
				.expectStatus()
				.isBadRequest();

	}

}
