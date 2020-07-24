package br.com.acme.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.acme.entities.Task;
import br.com.acme.entities.TaskItem;
import br.com.acme.services.TaskService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskResource {
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public ResponseEntity<List<Task>> findAll() {
		List<Task> tasks = taskService.findAll();
		
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> find(@PathVariable Long id) throws ObjectNotFoundException {
		Task task = taskService.find(id);
		
		return ResponseEntity.ok().body(task);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> insert(@Valid @RequestBody Task task) {
		task = taskService.insert(task);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(task.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Task updatedTask, @PathVariable Long id)
			throws ObjectNotFoundException {
		updatedTask.setId(id);
		// ideal criar um dto
		taskService.update(updatedTask, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
		taskService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/{task_id}/itens")
	public ResponseEntity<Void> insertItem( //
			@PathVariable Long task_id, //
			@Valid @RequestBody TaskItem taskItem) throws ObjectNotFoundException {
		
		Task task = taskService.find(task_id);
		taskService.insertItem(task, taskItem);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{task_id}/itens/{item_id}")
	public ResponseEntity<Void> updateItem( //
			@PathVariable Long task_id, //
			@Valid @RequestBody TaskItem updatedTaskItem, //
			@PathVariable Long item_id) throws ObjectNotFoundException {
		
		Task task = taskService.find(task_id);
		updatedTaskItem.setId(item_id);
		
		taskService.updateItem(task, updatedTaskItem);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{task_id}/itens/{item_id}")
	public ResponseEntity<Void> deleteItem( //
			@PathVariable Long task_id, //
			@PathVariable Long item_id) throws ObjectNotFoundException {

		taskService.deleteItem(item_id);
				
		return ResponseEntity.noContent().build();
	}
}
