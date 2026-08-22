package com.revisaoWeb.controller;

import com.revisaoWeb.service.CompartilhamentoService;
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
@RequestMapping ("/api/compartilhamento")
public class CompartilhamentoController {

    @Autowired
    private CompartilhamentoService compartilhamentoService;

    @PostMapping
    public String criar(@RequestBody Compartilhamento comp) {
        compartilhamentoService.criar(comp);
        return "Compartilhamento criado API.";
    }

    @GetMapping
    public List<Compartilhamento> listar() {
        return compartilhamentoService.listar();
    }

    @GetMapping("/{id}")
    Compartilhamento listarId(@PathVariable("id") int id) {
        return compartilhamentoService.listarId(id);
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable("id") int id, @RequestBody Compartilhamento comp) {
        compartilhamentoService.atualizar(id, comp);
        return "Compartilhamento atualizado API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        compartilhamentoService.deletar(id);
        return "Compartilhamento deletado API.";
    }

}
