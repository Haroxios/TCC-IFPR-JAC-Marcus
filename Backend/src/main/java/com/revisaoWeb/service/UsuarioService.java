package com.revisaoWeb.service;

import com.revisaoWeb.dao.UsuarioDao;
import com.revisaoWeb.dto.UsuarioReqDto;
import com.revisaoWeb.model.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioDao usuarioDao;
    //encoder == codificador >>> seja para crip ou descrip
    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void criar(Usuario usuario) {
        usuario.setSenha(criptografarSenha(usuario.getSenha()));

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
        UsuarioReqDto usuarioReqDto = new UsuarioReqDto(usuario);

        if (usuario == null) {
            throw new RuntimeException("Usuário ou senha inválidos.");
        }

        if (!encoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos.");
        }

        return usuarioReqDto;
    }

    public boolean verificarEmail(String email) {
        return usuarioDao.verificarEmail(email);
    }

    public String criptografarSenha(String senha) {

        if (senha == null || senha.isBlank()) {
            throw new RuntimeException("Senha é um campo obrigatório.");
        }

        String senhaCriptografada = encoder.encode(senha);

        return senhaCriptografada;
    }
}
