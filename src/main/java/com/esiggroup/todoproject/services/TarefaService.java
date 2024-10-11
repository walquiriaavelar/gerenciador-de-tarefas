package com.esiggroup.todoproject;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class TarefaService {

    @Inject
    private TarefaRepository tarefaRepository;

    public List<TarefaEntity> buscarPorFiltros(Integer numero, String tituloDescricao, ResponsavelEntity responsavel, String situacao) {
        return tarefaRepository.findByFilters(numero, tituloDescricao, responsavel, situacao);
    }

    public void removerTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }

    public void concluirTarefa(Integer id) {
        TarefaEntity tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setSituacao("CONCLUIDA");
        tarefaRepository.save(tarefa); 
    }
}
