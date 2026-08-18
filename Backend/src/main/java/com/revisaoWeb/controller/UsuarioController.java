package com.revisaoWeb.controller;

import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.service.UsuarioService;
import com.revisaoWeb.dto.LoginDto;
import com.revisaoWeb.dto.UsuarioReqDto;
import com.revisaoWeb.dto.EmailReqDto;

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
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public String criar(@RequestBody Usuario usuario) {
        usuarioService.criar(usuario);
        return "Usuario criado API.";
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping ("/{id}")
    public Usuario listarId(@PathVariable("id") int id) {
        return usuarioService.listarId(id);
    }

    @PutMapping ("/{id}")
    public String atualizar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        usuarioService.atualizar(id, usuario);
        return "Usuario atualizado API.";
    }

    @DeleteMapping ("/{id}")
    public String deletar(@PathVariable("id") int id) {
        usuarioService.deletar(id);
        return "usuario deletado API.";
    }

    @PostMapping ("/login")
    public UsuarioReqDto login(@RequestBody LoginDto loginDto) {
        String email = loginDto.getEmail();
        String senha = loginDto.getSenha();
        return usuarioService.logar(email, senha);
    }

    @PostMapping ("/verificarEmail")
    public boolean verificarEmail(@RequestBody EmailReqDto emailReqDto) {
        String email = emailReqDto.getEmail();
        return usuarioService.verificarEmail(email);
    }
}
