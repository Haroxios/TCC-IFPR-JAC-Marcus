package com.revisaoWeb.dao;

import org.springframework.stereotype.Repository;

import com.revisaoWeb.model.Compartilhamento;
import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Usuario;
import com.revisaoWeb.model.Documento;
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
        String sql = "INSERT INTO compartilhamentos (permissao, idUsuarioFK, idDocumentoFK) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, comp.getPermissao().name());
            ps.setInt(2, comp.getUsuario().getIdUsuario());
            ps.setInt(3, comp.getDocumento().getIdDocumento());

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

                usuario.setIdUsuario(rs.getInt("idUsuarioFK"));
                documento.setIdDocumento(rs.getInt("idDocumentoFK"));

                comp.setIdCompartilhamento(rs.getInt("idCompartilhamento"));
                comp.setPermissao(Permissao.valueOf(rs.getString("permissao")));
                comp.setUsuario(usuario);
                comp.setDocumento(documento);

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
        String sql = "SELECT * FROM compartilhamentos WHERE idCompartilhamento = ?";
        Compartilhamento comp;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comp = new Compartilhamento();
                Usuario usr = new Usuario();
                Documento doc = new Documento();

                usr.setIdUsuario(rs.getInt("idUsuarioFK"));
                doc.setIdDocumento(rs.getInt("idDocumentoFK"));

                comp.setIdCompartilhamento(rs.getInt("idCompartilhamento"));
                comp.setPermissao(Permissao.valueOf(rs.getString("permissao")));
                comp.setUsuario(usr);
                comp.setDocumento(doc);
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

    public void atualizar(int id, Compartilhamento comp) {
        String sql = "UPDATE compartilhamentos SET permissao = ?, idUsuarioFK = ?, idDocumentoFK = ? WHERE idCompartilhamento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, comp.getPermissao().name());
            ps.setInt(2, comp.getUsuario().getIdUsuario());
            ps.setInt(3, comp.getDocumento().getIdDocumento());
            ps.setInt(4, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar compartilhamento: " +e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM compartilhamentos WHERE idCompartilhamento = ?";

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
