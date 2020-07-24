package br.com.acme.builders;

import static br.com.acme.builders.TaskItemBuilder.buildTaskItem;

import java.util.ArrayList;
import java.util.List;

import br.com.acme.entities.Task;
import br.com.acme.entities.TaskItem;

public class TaskBuilder {
	
	private static final String TITLE = "titulo";

	public static Task buildTask() {
		List<TaskItem> items = new ArrayList<>();
		items.add(buildTaskItem());

		Task task = new Task();
		task.setTitle(TITLE);
		task.setItems(items);
		
		return task;
	}

}
