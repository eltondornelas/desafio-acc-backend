package br.com.acme.services;

import static br.com.acme.builders.TaskBuilder.buildTask;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.rules.ExpectedException.none;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.acme.entities.Task;
import br.com.acme.repositories.TaskRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	private static final long ID_DUMMY = 0L;

	@Rule
	public ExpectedException thrown = none();
	
	@InjectMocks
	private TaskService taskService = new TaskService();
	
	@Mock
	private TaskRepository taskRepository;
	
	@Test
	public void deveLancarExcecaoQuandoObjetoNaoEncontradoNoRepositorio() throws ObjectNotFoundException {
		thrown.expect(ObjectNotFoundException.class);
		thrown.expectMessage("Tarefa não encontrada");
		
		when(taskRepository.findById(ID_DUMMY)).thenReturn(ofNullable(null));
		
		taskService.find(ID_DUMMY);
	}
	
	@Test
	public void deveRetornarTaskQuandoObjetoEncontradoNoRepositorio() throws ObjectNotFoundException {
		Task retornoEsperado = buildTask();
		
		Optional<Task> optional = Optional.of(retornoEsperado);
		
		when(taskRepository.findById(ID_DUMMY)).thenReturn(optional);
		
		Task retornoRecebido = taskService.find(ID_DUMMY);
		
		assertEquals(retornoEsperado, retornoRecebido);
	}
}
