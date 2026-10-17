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
        String sql = "INSERT INTO cadernos (caderno, idUsuarioFK) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, caderno.getCaderno());
            ps.setInt(2, caderno.getUsuario().getIdUsuario());

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

                usuario.setIdUsuario(rs.getInt("idUsuarioFK"));

                caderno.setIdCaderno(rs.getInt("idCaderno"));
                caderno.setCaderno(rs.getString("caderno"));
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
        String sql = "SELECT * FROM cadernos WHERE idCaderno = ?";
        Caderno caderno;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                caderno = new Caderno();
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuarioFK"));

                caderno.setIdCaderno(rs.getInt("idCaderno"));
                caderno.setCaderno(rs.getString("caderno"));
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

    public void atualizar (int id, Caderno caderno) {
        String sql = "UPDATE cadernos SET caderno = ?, idUsuarioFK = ? WHERE idCaderno = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, caderno.getCaderno());
            ps.setInt(2, caderno.getUsuario().getIdUsuario());
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("Caderno atualizado.");
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar caderno: " +e.getMessage());
        }
    }


    public void deletar(int id) {
        String sql = "DELETE FROM cadernos WHERE idCaderno = ?";

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

    public List<Caderno> listarCadernosUsuario(int idUsuarioFK) {
        String sql = "SELECT * FROM cadernos WHERE idUsuarioFK = ?";
        List<Caderno> cadernosUsuario = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, idUsuarioFK);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Caderno caderno = new Caderno();
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(idUsuarioFK);

                caderno.setIdCaderno(rs.getInt("idCaderno"));
                caderno.setCaderno(rs.getString("caderno"));
                caderno.setUsuario(usuario);

                cadernosUsuario.add(caderno);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cadernos do usuário" +e.getMessage());
        }

        System.out.println("Cadernos de usuário listado.");
        return cadernosUsuario;
    }
}


