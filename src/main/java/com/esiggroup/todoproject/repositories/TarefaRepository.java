package com.esiggroup.todoproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TarefaEntity> findByFilters(Integer numero, String tituloDescricao, ResponsavelEntity responsavel, String situacao) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TarefaEntity> cq = cb.createQuery(TarefaEntity.class);

        Root<TarefaEntity> tarefa = cq.from(TarefaEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (numero != null) {
            predicates.add(cb.equal(tarefa.get("numero"), numero));
        }

        if (tituloDescricao != null && !tituloDescricao.isEmpty()) {
            predicates.add(cb.or(
                cb.like(cb.lower(tarefa.get("titulo")), "%" + tituloDescricao.toLowerCase() + "%"),
                cb.like(cb.lower(tarefa.get("descricao")), "%" + tituloDescricao.toLowerCase() + "%")
            ));
        }

        if (responsavel != null) {
            predicates.add(cb.equal(tarefa.get("responsavel"), responsavel));
        }

        if (situacao != null && !situacao.isEmpty()) {
            predicates.add(cb.equal(tarefa.get("situacao"), situacao));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public void deleteById(Integer id) {
        TarefaEntity tarefa = entityManager.find(TarefaEntity.class, id);
        if (tarefa != null) {
            entityManager.remove(tarefa);
        }
    }

    @Transactional
    public void save(TarefaEntity tarefa) {
        if (tarefa.getId() == null) {
            entityManager.persist(tarefa);
        } else {
            entityManager.merge(tarefa);
        }
    }

    public TarefaEntity findById(Integer id) {
        return entityManager.find(TarefaEntity.class, id);
    }
}
