package com.ohhigordev.todolist.Service;

import com.ohhigordev.todolist.Dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO criar(TaskResponseDTO dto);
    List<TaskResponseDTO> listarTodas();
    TaskResponseDTO buscarPorId(Long id);
    TaskResponseDTO atualizar(Long id, TaskResponseDTO dto);
    void deletar(Long id);

    // Método para listar as tarefas por status
     List<TaskResponseDTO> listarPosStatus(Boolean concluida);


}
