package com.esiggroup.todoproject;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

    private TarefaEntity tarefa;
    private List<TarefaEntity> tarefasFiltradas;
    private List<ResponsavelEntity> listaResponsaveis;

    // Filtros
    private Integer filtroNumero;
    private String filtroTituloDescricao;
    private ResponsavelEntity filtroResponsavel;
    private String filtroSituacao;

    private final TarefaService tarefaService;
    private final ResponsavelService responsavelService;

    public TarefaBean(TarefaService tarefaService, ResponsavelService responsavelService) {
        this.tarefaService = tarefaService;
        this.responsavelService = responsavelService;
    }

    @PostConstruct
    public void init() {
        listaResponsaveis = responsavelService.buscarTodos();
        buscarTarefas(); // Carrega a lista de tarefas inicialmente
    }

    public void buscarTarefas() {
        tarefasFiltradas = tarefaService.buscarPorFiltros(filtroNumero, filtroTituloDescricao, filtroResponsavel, filtroSituacao);
    }

    public void editarTarefa(Integer id) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Redirecionando para a edição da tarefa " + id));
    }

    public void excluirTarefa(Integer id) {
        tarefaService.removerTarefa(id);
        buscarTarefas();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Tarefa excluída com sucesso!"));
    }

    public void concluirTarefa(Integer id) {
        tarefaService.concluirTarefa(id); 
        buscarTarefas(); 
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Tarefa concluída com sucesso!"));
    }

    // Getters e Setters

    public List<TarefaEntity> getTarefasFiltradas() {
        return tarefasFiltradas;
    }

    public Integer getFiltroNumero() {
        return filtroNumero;
    }

    public void setFiltroNumero(Integer filtroNumero) {
        this.filtroNumero = filtroNumero;
    }

    public String getFiltroTituloDescricao() {
        return filtroTituloDescricao;
    }

    public void setFiltroTituloDescricao(String filtroTituloDescricao) {
        this.filtroTituloDescricao = filtroTituloDescricao;
    }

    public ResponsavelEntity getFiltroResponsavel() {
        return filtroResponsavel;
    }

    public void setFiltroResponsavel(ResponsavelEntity filtroResponsavel) {
        this.filtroResponsavel = filtroResponsavel;
    }

    public String getFiltroSituacao() {
        return filtroSituacao;
    }

    public void setFiltroSituacao(String filtroSituacao) {
        this.filtroSituacao = filtroSituacao;
    }

    public List<ResponsavelEntity> getListaResponsaveis() {
        return listaResponsaveis;
    }
}
