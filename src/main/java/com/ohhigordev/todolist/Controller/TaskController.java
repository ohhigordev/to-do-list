package com.ohhigordev.todolist.Controller;


import com.ohhigordev.todolist.Dto.TaskResponseDTO;
import com.ohhigordev.todolist.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public TaskResponseDTO criarTarefa(@Valid @RequestBody TaskResponseDTO dto) {
        return taskService.criar(dto);
    }

    @GetMapping
    public List<TaskResponseDTO> listarTarefas() {
        return taskService.listarTodas();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO buscarTarefa(@PathVariable Long id) {
        return taskService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO atualizarTarefa(@PathVariable Long id,@Valid @RequestBody TaskResponseDTO dto) {
        return taskService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        taskService.deletar(id);
    }

    // Filtrando as tarefas por status
    @GetMapping("/status")
    public List<TaskResponseDTO> listarTarefasPorStatus(@RequestParam Boolean concluida){
        return taskService.listarPosStatus(concluida);
    }
}
