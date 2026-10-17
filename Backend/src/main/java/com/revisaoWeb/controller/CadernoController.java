package com.revisaoWeb.controller;

import com.revisaoWeb.service.CadernoService;
import com.revisaoWeb.model.Caderno;

import java.util.List;

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
@RequestMapping("/api/caderno")
public class CadernoController {

    @Autowired
    private CadernoService cadernoService;

    @PostMapping
    public String criar(@RequestBody Caderno caderno) {
        cadernoService.criar(caderno);
        return "Caderno cadastrado API.";
    }

    @GetMapping
    public List<Caderno> listar() {
        return cadernoService.listar();
    }

    @GetMapping("/{id}")
    public Caderno listarId(@PathVariable("id") int id) {
        return cadernoService.listarId(id);
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable("id") int id, @RequestBody Caderno caderno) {
        cadernoService.atualizar(id, caderno);
        return "Caderno atualizado API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        cadernoService.deletar(id);
        return "Caderno deletado API.";
    }

    @PostMapping("/usuario/{id}")
    public List<Caderno> listarCadernosUsuario(@PathVariable("id") int idUsuario) {
        return cadernoService.listarCadernosUsuario(idUsuario);
    }
}
