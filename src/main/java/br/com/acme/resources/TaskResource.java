package br.com.acme.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.entities.Task;

@RestController
@RequestMapping("/tarefas")
public class TaskResource {

	@GetMapping("/")
	public ResponseEntity<Task> findAll() {
		
		Task tarefa = new Task();
		tarefa.setDescription("descrição");
		tarefa.setTitle("titulo");
		tarefa.setId(111L);
		
		return ResponseEntity.ok(tarefa);
	}
}
