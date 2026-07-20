package com.revisaoWeb.controller;

import com.revisaoWeb.model.Tag;
import com.revisaoWeb.dao.TagDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping ("/tag")
public class TagController {

    @Autowired
    private TagDao tagDao;

    @PostMapping
    public String criar(@RequestBody Tag tag) {
        tagDao.criar(tag);
        return "Tag criada API.";
    }

    @GetMapping
    public List<Tag> listar() {
        return tagDao.listar();
    }

    @GetMapping ("/{id}")
    public Tag listarId(@PathVariable("id") int id) {
        return tagDao.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Tag tag) {
        tagDao.atualizar(tag);
        return "Tag atualizarda API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        tagDao.deletar(id);
        return "Tag deletada API.";
    }
}
