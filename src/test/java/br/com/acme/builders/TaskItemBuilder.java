package br.com.acme.builders;

import br.com.acme.entities.Task;
import br.com.acme.entities.TaskItem;

public class TaskItemBuilder {
	
	private static final long TASK_ITEM_ID = 7L;
	private static final String DESCRIPTION = "descricao";

	public static TaskItem buildTaskItem() {
		TaskItem taskItem = new TaskItem();
		taskItem.setId(TASK_ITEM_ID);
		taskItem.setDescription(DESCRIPTION);
		taskItem.setTask(new Task());
		
		return taskItem;
	}

}
