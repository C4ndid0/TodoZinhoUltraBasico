package br.com.candidoLucas.todozinho.repository;

import br.com.candidoLucas.todozinho.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
