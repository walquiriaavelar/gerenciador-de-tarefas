package com.esiggroup.todoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping
    public List<ResponsavelEntity> listarResponsaveis() {
        return responsavelService.listarResponsaveis();
    }

    @PostMapping
    public ResponsavelEntity criarResponsavel(@RequestBody ResponsavelEntity responsavel) {
        return responsavelService.criarResponsavel(responsavel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelEntity> buscarResponsavelPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(responsavelService.buscarResponsavelPorId(id));
    }
}
