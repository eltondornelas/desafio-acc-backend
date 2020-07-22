package br.com.acme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.entities.Task;
import br.com.acme.entities.TaskItem;
import br.com.acme.repositories.TaskItemRepository;
import br.com.acme.repositories.TaskRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskItemRepository taskItemRepository;
	
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	public Task find(Long id) throws ObjectNotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		
		return task.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
	}
	
	public Task insert(Task task) {
		task.setId(null);
		return taskRepository.save(task);
	}
	
	public Task update(Task updatedTask, Long id) throws ObjectNotFoundException {
		Task task = find(id);
		updateTask(task, updatedTask);
		
		return taskRepository.save(task);
	}

		
	public void delete(Long id) throws ObjectNotFoundException {
		Task task = find(id);
		
		taskRepository.delete(task);
	}
	
	private void updateTask(Task task, Task updatedTask) {
		task.setTitle(updatedTask.getTitle());
	}
	
	public TaskItem findItem(Long id) throws ObjectNotFoundException {
		Optional<TaskItem> taskItem =  taskItemRepository.findById(id);
		
		return taskItem.orElseThrow(() -> new ObjectNotFoundException("Item não encontrado"));
	}
	
	public Task insertItem(Task task, TaskItem taskItem) {
		taskItem.setId(null);
		taskItem.setTask(task);
		taskItem = taskItemRepository.save(taskItem);
		
		task.getItems().add(taskItem);
		
		return taskRepository.save(task);
	}
	
	public void updateItem(Task task, TaskItem updatedTaskItem) throws ObjectNotFoundException {
		TaskItem taskItem = findItem(updatedTaskItem.getId());
		taskItem.setDescription(updatedTaskItem.getDescription());
		
		taskItemRepository.save(taskItem);;
	}
	
	
	public void deleteItem(Long itemId) throws ObjectNotFoundException {
		TaskItem taskItem = findItem(itemId);
		
		taskItemRepository.delete(taskItem);
	}
}
