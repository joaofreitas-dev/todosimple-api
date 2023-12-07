package com.joaofreitas.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofreitas.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
