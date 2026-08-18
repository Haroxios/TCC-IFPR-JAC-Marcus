package com.revisaoWeb.dao;

import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao {

    public void criar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            ps.executeUpdate();
            System.out.println("Usuario cadastrato.");

        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuario: " +e.getMessage());
        }
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usr = new Usuario();

                usr.setId(rs.getInt("id"));
                usr.setNome(rs.getString("nome"));
                usr.setEmail(rs.getString("email"));
                usr.setSenha(rs.getString("senha"));

                listaUsuarios.add(usr);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuarios: " +e.getMessage());
        }

        return listaUsuarios;
    }

    public Usuario listarId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usr;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usr = new Usuario();

                usr.setId(rs.getInt("id"));
                usr.setNome(rs.getString("nome"));
                usr.setEmail(rs.getString("email"));
                usr.setSenha(rs.getString("senha"));
            }
            else {
                usr = null;
            }
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuario: " +e.getMessage());
        }

        return usr;
    }

    public void atualizar(int id, Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, id);

            ps.executeUpdate();

            System.out.println("Usuario atualizado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuario: " +e.getMessage());
        }
    }

    public void deletar (int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario deletado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuario: " +e.getMessage());
        }
    }

    public Usuario logar(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usr;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usr = new Usuario();

                usr.setId(rs.getInt("id"));
                usr.setEmail(rs.getString("email"));
                usr.setNome(rs.getString("nome"));
                usr.setSenha(rs.getString("senha"));
            }
            else {
                usr = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario: " +e.getMessage());
        }

        return usr;
    }

    public boolean verificarEmail(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        boolean existe = false;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            existe = rs.next();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario: " +e.getMessage());
        }

        return existe;
    }
}
