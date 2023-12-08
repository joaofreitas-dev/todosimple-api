package com.joaofreitas.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaofreitas.todosimple.models.Task;
import com.joaofreitas.todosimple.models.User;
import com.joaofreitas.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserServices userServices;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(
                () -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userServices.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObjt = findById(obj.getId());
        newObjt.setDescription(obj.getDescription());
        return this.taskRepository.save(newObjt);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relaciondas!");
        }
    }

}
