package com.ohhigordev.todolist.Service.impl;

import com.ohhigordev.todolist.Dto.TaskResponseDTO;
import com.ohhigordev.todolist.Exception.TarefaNaoEncontradaException;
import com.ohhigordev.todolist.Model.Task;
import com.ohhigordev.todolist.Repository.TaskRepository;
import com.ohhigordev.todolist.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDTO criar(@Valid TaskResponseDTO dto) {
        Task task = new Task();
        task.setDescricao(dto.getDescricao());
        task.setConcluida(dto.getConcluida());
        task.setPrazo(dto.getPrazo());
        Task salva = taskRepository.save(task);
        return toReponseDTO(salva);
    }



    @Override
    public List<TaskResponseDTO> listarTodas() {
        return taskRepository.findAll()
                .stream()
                .map(this::toReponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public TaskResponseDTO buscarPorId(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
        return toReponseDTO(task);
    }

    @Override
    public TaskResponseDTO atualizar(Long id,@Valid TaskResponseDTO dto) {
        Task task = taskRepository.findById(id)
                .map(t -> {
                    t.setDescricao(dto.getDescricao());
                    t.setConcluida(dto.getConcluida());
                    t.setPrazo(dto.getPrazo());
                    return taskRepository.save(t);
                })
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
        return toReponseDTO(task);
    }

    @Override
    public void deletar(Long id) {
        taskRepository.deleteById(id);
    }

    // Implementado o filtro por status
    @Override
    public List<TaskResponseDTO> listarPosStatus(Boolean concluida) {
        List<Task> tarefas = concluida == null
                ? taskRepository.findAll()
                : taskRepository.findByConcluida(concluida);

        return tarefas.stream()
                .map(this::toReponseDTO)
                .collect(Collectors.toList());
    }

    private TaskResponseDTO toReponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setDescricao(task.getDescricao());
        dto.setConcluida(task.isConcluida());
        dto.setPrazo(task.getPrazo());
        dto.setDataCriacao(task.getDataCriacao());
        return dto;
    }
}
