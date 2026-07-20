package com.revisaoWeb.controller;

import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.dao.UsuarioDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @PostMapping
    public String criar(@RequestBody Usuario usuario) {
        usuarioDao.criar(usuario);
        return "Usuario criardo API";
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    @GetMapping ("/{id}")
    public Usuario listarId(@PathVariable("id") int id) {
        return usuarioDao.listarId(id);
    }

    @PutMapping
    public String atualizar(@RequestBody Usuario usuario) {
        usuarioDao.atualizar(usuario);
        return "Usuario atualizado API";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        usuarioDao.deletar(id);
        return "usuario deletado API";
    }
}
