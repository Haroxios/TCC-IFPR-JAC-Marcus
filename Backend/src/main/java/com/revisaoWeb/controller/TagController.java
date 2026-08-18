package com.revisaoWeb.controller;

import com.revisaoWeb.model.Tag;
import com.revisaoWeb.service.TagService;

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
@RequestMapping ("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public String criar(@RequestBody Tag tag) {
        tagService.criar(tag);
        return "Tag criada API.";
    }

    @GetMapping
    public List<Tag> listar() {
        return tagService.listar();
    }

    @GetMapping ("/{id}")
    public Tag listarId(@PathVariable("id") int id) {
        return tagService.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Tag tag) {
        tagService.atualizar(tag);
        return "Tag atualizarda API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        tagService.deletar(id);
        return "Tag deletada API.";
    }
}
