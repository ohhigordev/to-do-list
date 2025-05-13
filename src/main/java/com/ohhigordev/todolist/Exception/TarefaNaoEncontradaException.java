package com.ohhigordev.todolist.Exception;

public class TarefaNaoEncontradaException extends RuntimeException{
    public TarefaNaoEncontradaException(Long id){
        super("Tarefa com ID " + id + " não encontrada");
    }
}
