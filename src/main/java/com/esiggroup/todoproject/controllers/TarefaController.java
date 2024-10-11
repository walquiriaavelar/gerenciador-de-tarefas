package com.esiggroup.todoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaEntity> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @PostMapping
    public TarefaEntity criarTarefa(@RequestBody TarefaEntity tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaEntity> atualizarTarefa(@PathVariable Integer id, @RequestBody TarefaEntity tarefa) {
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Integer id) {
        tarefaService.removerTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaEntity> buscarTarefaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tarefaService.buscarTarefaPorId(id));
    }
}
