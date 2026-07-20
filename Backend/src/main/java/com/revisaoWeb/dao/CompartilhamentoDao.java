package com.revisaoWeb.dao;

import org.springframework.stereotype.Repository;

import com.revisaoWeb.model.Compartilhamento;
import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.model.Permissao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

@Repository
public class CompartilhamentoDao {

    public void criar(Compartilhamento comp) {
        String sql = "INSERT INTO compartilhamentos (permissao, idUsuario, idDocumento, idCaderno) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, comp.getPermissao().name());
            ps.setInt(2, comp.getUsuario().getId());
            ps.setInt(3, comp.getDocumento().getId());
            ps.setInt(4, comp.getCaderno().getId());

            ps.executeUpdate();
            System.out.println("Compartilhamento criado.");

        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao criar compartilhamento: " +e.getMessage());
        }
    }

    public List<Compartilhamento> listar() {
        String sql = "SELECT * FROM compartilhamentos";
        List<Compartilhamento> listaComp = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();) {

            while(rs.next()) {
                Compartilhamento comp = new Compartilhamento();
                Usuario usuario = new Usuario();
                Documento documento = new Documento();
                Caderno caderno = new Caderno();

                usuario.setId(rs.getInt("idUsuario"));
                documento.setId(rs.getInt("idDocumento"));
                caderno.setId(rs.getInt("idCaderno"));

                comp.setId(rs.getInt("id"));
                comp.setPermissao(Permissao.valueOf(rs.getString("permissao")));
                comp.setUsuario(usuario);
                comp.setDocumento(documento);
                comp.setCaderno(caderno);

                listaComp.add(comp);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compartilhamento: " +e.getMessage());
        }

        System.out.println("Compartilhamento listado.");
        return listaComp;
    }

    public Compartilhamento listarId(int id) {
        String sql = "SELECT * FROM compartilhamentos WHERE id = ?";
        Compartilhamento comp;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comp = new Compartilhamento();
                Usuario usr = new Usuario();
                Documento doc = new Documento();
                Caderno cad = new Caderno();

                usr.setId(rs.getInt("idUsuario"));
                doc.setId(rs.getInt("idDocumento"));
                cad.setId(rs.getInt("idCaderno"));

                comp.setId(rs.getInt("id"));
                comp.setPermissao(Permissao.valueOf(rs.getString("permissao")));
                comp.setUsuario(usr);
                comp.setDocumento(doc);
                comp.setCaderno(cad);
            }
            else {
                comp = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compartilhamento: " +e.getMessage());
        }

        return comp;
    }

    public void atualizar(Compartilhamento comp) {
        String sql = "UPDATE compartilhamentos SET permissao = ?, idUsuario = ?, idDocumento = ?, idCaderno = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, comp.getPermissao().name());
            ps.setInt(2, comp.getUsuario().getId());
            ps.setInt(3, comp.getDocumento().getId());
            ps.setInt(4, comp.getCaderno().getId());
            ps.setInt(5, comp.getId());

            ps.executeUpdate();
            System.out.println("Documento atualizado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar compartilhamento: " +e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM compartilhamentos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Compartilhamento deletado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar compartilhamento: " +e.getMessage());
        }
    }
}
