package com.revisaoWeb.controller;

import com.revisaoWeb.dao.CompartilhamentoDao;
import com.revisaoWeb.model.Compartilhamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping ("/compartilhamento")
public class CompartilhamentoController {

    @Autowired
    private CompartilhamentoDao compDao;

    @PostMapping
    public String criar(@RequestBody Compartilhamento comp) {
        compDao.criar(comp);
        return "Compartilhamento criado API.";
    }

    @GetMapping
    public List<Compartilhamento> listar() {
        return compDao.listar();
    }

    @GetMapping("/{id}")
    Compartilhamento listarId(@PathVariable("id") int id) {
        return compDao.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Compartilhamento comp) {
        compDao.atualizar(comp);
        return "Compartilhamento atualizado API";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        compDao.deletar(id);
        return "Compartilhamento deletado API";
    }

}
