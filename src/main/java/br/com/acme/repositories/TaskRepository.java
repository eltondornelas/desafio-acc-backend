package br.com.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acme.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
