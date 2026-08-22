package com.revisaoWeb.controller;

import java.util.List;

import com.revisaoWeb.service.DocumentoService;
import com.revisaoWeb.model.Documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping ("/api/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public String criar(@RequestBody Documento documento) {
        documentoService.criar(documento);
        return "Documento criado API.";
    }

    @GetMapping
    public List<Documento> listar() {
        return documentoService.listar();
    }

    @GetMapping("/{id}")
    public Documento listarId(@PathVariable("id") int id) {
        return documentoService.listarId(id);
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable("id") int id, @RequestBody Documento documento) {
        documentoService.atualizar(id, documento);
        return "Documento atualizado.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        documentoService.deletar(id);
        return "Documento deletado API.";
    }
}
