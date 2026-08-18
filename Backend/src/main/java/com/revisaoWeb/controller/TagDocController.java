package com.revisaoWeb.controller;

import com.revisaoWeb.service.TagDocService;
import com.revisaoWeb.model.TagDoc;

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
@RequestMapping ("/api/tagDoc")
public class TagDocController {

    @Autowired
    private TagDocService tagDocService;

    @PostMapping
    public String criar(@RequestBody TagDoc td) {
        tagDocService.criar(td);
        return "TagDoc criada API.";
    }

    @GetMapping
    public List<TagDoc> listar() {
        return tagDocService.listar();
    }

    @GetMapping ("/{id}")
    public TagDoc listarId(@PathVariable("id") int id) {
        return tagDocService.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody TagDoc td) {
        tagDocService.atualizar(td);
        return "TagDoc atualizado API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        tagDocService.deletar(id);
        return "TagDoc deletado API.";
    }
}
