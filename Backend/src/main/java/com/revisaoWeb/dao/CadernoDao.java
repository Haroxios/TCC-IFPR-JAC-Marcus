package com.revisaoWeb.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.config.DatabaseConnection;

@Repository
public class CadernoDao {

    public void criar (Caderno caderno) {
        String sql = "INSERT INTO cadernos (nome, idUsuario) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, caderno.getNome());
            ps.setInt(2, caderno.getUsuario().getId());

            ps.executeUpdate();
            System.out.println("Caderno Cadastrado");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar caderno: " +e.getMessage());
        }
    }

    public List<Caderno> listar() {
        String sql = "SELECT * FROM cadernos"; //talvez fazer um inner join com a tabela de usuario ou outra rota e tal
        List<Caderno> listaCaderno = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Caderno caderno = new Caderno();
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("idUsuario"));

                caderno.setId(rs.getInt("id"));
                caderno.setNome(rs.getString("nome"));
                caderno.setUsuario(usuario);

                listaCaderno.add(caderno);
            }
        }

        catch (SQLException e) {
            throw new RuntimeException ("Erro ao listar cadernos: " +e.getMessage());
        }

        System.out.println("Caderno listado.");
        return listaCaderno;
    }

    public Caderno listarId (int id) {
        String sql = "SELECT * FROM cadernos WHERE id = ?";
        Caderno caderno;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                caderno = new Caderno();
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("idUsuario"));

                caderno.setId(rs.getInt("id"));
                caderno.setNome(rs.getString("nome"));
                caderno.setUsuario(usuario);
            }
            else {
                caderno = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar caderno: " +e.getMessage());
        }

        return caderno;
    }

    public void atualizar (Caderno caderno) {
        String sql = "UPDATE cadernos SET nome = ?, idUsuario = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, caderno.getNome());
            ps.setInt(2, caderno.getUsuario().getId());
            ps.setInt(3, caderno.getId());

            ps.executeUpdate();
            System.out.println("Caderno atualizado.");
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar caderno: " +e.getMessage());
        }
    }


    public void deletar(int id) {
        String sql = "DELETE FROM cadernos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Caderno deletado.");
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar caderno: " +e.getMessage());
        }
    }
}


