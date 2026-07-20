package com.revisaoWeb.controller;

import com.revisaoWeb.dao.CadernoDao;
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
@RequestMapping("/caderno")
public class CadernoController {

    @Autowired
    private CadernoDao cadernoDao;

    @PostMapping
    public String criar(@RequestBody Caderno caderno) {
        cadernoDao.criar(caderno);
        return "Caderno cadastrado API";
    }

    @GetMapping
    public List<Caderno> listar() {
        return cadernoDao.listar();
    }

    @GetMapping ("/{id}")
    public Caderno listarId(@PathVariable("id") int id) {
        return cadernoDao.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Caderno caderno) {
        cadernoDao.atualizar(caderno);
        return "Caderno atualizado API";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        cadernoDao.deletar(id);
        return "Caderno deletado API";
    }
}
