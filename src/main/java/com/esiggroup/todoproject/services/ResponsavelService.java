package com.esiggroup.todoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public List<ResponsavelEntity> listarResponsaveis() {
        return responsavelRepository.findAll();
    }

    public ResponsavelEntity criarResponsavel(ResponsavelEntity responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public ResponsavelEntity buscarResponsavelPorId(Integer id) {
        return responsavelRepository.findById(id).orElseThrow(() -> new RuntimeException("Responsável não encontrado!"));
    }
}
