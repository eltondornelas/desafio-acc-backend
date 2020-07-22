package br.com.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acme.entities.TaskItem;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {

}
