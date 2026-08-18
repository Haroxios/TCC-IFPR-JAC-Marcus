package com.revisaoWeb.service;

import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.dao.UsuarioDao;
import com.revisaoWeb.dto.UsuarioReqDto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void criar(Usuario usuario) {
        //String senha = usuario.getSenha();
        //String senhaCriptografada = senha;

        usuarioDao.criar(usuario);
    }

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public Usuario listarId(int id) {
        return usuarioDao.listarId(id);
    }

    public void atualizar(int id, Usuario usuario) {
        usuarioDao.atualizar(id, usuario);
    }

    public void deletar(int id) {
        usuarioDao.deletar(id);
    }

    public UsuarioReqDto logar(String email, String senha) {
        Usuario usuario = usuarioDao.logar(email);
        //String token = "Futuro token e talz";

        if (usuario == null) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        if (!senha.equals(usuario.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        //System.out.println(usuario);
        return new UsuarioReqDto(usuario);
    }

    public boolean verificarEmail(String email) {
        return usuarioDao.verificarEmail(email);
    }
}
