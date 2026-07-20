package com.revisaoWeb.controller;

import java.util.List;

import com.revisaoWeb.dao.DocumentoDao;
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
@RequestMapping ("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoDao documentoDao;

    @PostMapping
    public String criar(@RequestBody Documento documento) {
        documentoDao.criar(documento);
        return "Documento criado API";
    }

    @GetMapping
    public List<Documento> listar() {
        return documentoDao.listar();
    }

    @GetMapping("/{id}")
    public Documento listarId(@PathVariable("id") int id) {
        return documentoDao.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Documento documento) {
        documentoDao.atualizar(documento);
        return "Documento atualizado";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        documentoDao.deletar(id);
        return "Documento deletado API";
    }
}
