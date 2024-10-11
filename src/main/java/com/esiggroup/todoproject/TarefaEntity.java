package com.esiggroup.todoproject;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tarefas") // Correspondente à tabela SQL
public class TarefaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // Enum para representar a prioridade
    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    // Enum para representar a situação
    public enum Situacao {
        EM_ANDAMENTO, CONCLUIDA, CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL) // Cascata caso necessário
    @JoinColumn(name = "responsavel", referencedColumnName = "id", nullable = false)
    private ResponsavelEntity responsavel;

    @Enumerated(EnumType.STRING) // Mapeia o enum como String no banco de dados
    @Column(name = "prioridade", nullable = false, length = 10)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING) // Mapeia o enum como String no banco de dados
    @Column(name = "situacao", nullable = false, length = 20)
    private Situacao situacao = Situacao.EM_ANDAMENTO; // Valor padrão

    @Temporal(TemporalType.DATE)
    @Column(name = "deadline")
    private Date deadline;

    // Getters e Setters
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ResponsavelEntity getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelEntity responsavel) {
        this.responsavel = responsavel;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    // hashCode, equals e toString
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TarefaEntity)) {
            return false;
        }
        TarefaEntity other = (TarefaEntity) object;
        return !((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero)));
    }

    @Override
    public String toString() {
        return "com.esiggroup.todoproject.TarefaEntity[ numero=" + numero + " ]";
    }
}
