package com.revisaoWeb.dao;

import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentoDao {

    public void criar(Documento documento) {
        String sql = "INSERT INTO documentos (conteudo, dataCriacao, dataUltimaEdicao, statusRevisao, proximaRevisao, idCaderno) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getConteudo());
            ps.setDate(2, documento.getDataCriacao());
            ps.setDate(3, documento.getDataUltimaEdicao());
            ps.setString(4, documento.getStatusRevisao().name()); //.name() puxa em string a informação
            ps.setDate(5, documento.getProximaRevisao());
            ps.setInt(6, documento.getCaderno().getId());

            ps.executeUpdate();
            System.out.println("Documento criado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao criar Documento: " +e.getMessage());
        }
    }

    public List<Documento> listar() {
        String sql = "SELECT * FROM documentos";
        List<Documento> listaDocumentos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Documento documento = new Documento();
                Caderno caderno = new Caderno();

                caderno.setId(rs.getInt("idCaderno"));

                documento.setId(rs.getInt("id"));
                documento.setConteudo(rs.getString("conteudo"));
                documento.setDataCriacao(rs.getDate("dataCriacao"));
                documento.setDataUltimaEdicao(rs.getDate("dataUltimaEdicao"));
                documento.setStatusRevisao(Status.valueOf(rs.getString("statusRevisao"))); // 'Contrário' para status
                documento.setProximaRevisao(rs.getDate("proximaRevisao"));
                documento.setCaderno(caderno);

                listaDocumentos.add(documento);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar documentos: " +e.getMessage());
        }

        System.out.println("documento listado");
        return listaDocumentos;
    }

    public Documento listarId(int id) {
        String sql = "SELECT * FROM documentos WHERE id = ?";
        Documento doc;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doc = new Documento();
                Caderno cad = new Caderno();

                cad.setId(rs.getInt("idCaderno"));

                doc.setId(rs.getInt("id"));
                doc.setConteudo(rs.getString("conteudo"));
                doc.setDataCriacao(rs.getDate("dataCriacao"));
                doc.setDataUltimaEdicao(rs.getDate("dataUltimaEdicao"));
                doc.setStatusRevisao(Status.valueOf(rs.getString("StatusRevisao")));
                doc.setProximaRevisao(rs.getDate("proximaRevisao"));
                doc.setCaderno(cad);
            }
            else {
                doc = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar documento: " +e.getMessage());
        }

        return doc;
    }

    public void atualizar(Documento documento) {
        String sql = "UPDATE documentos SET conteudo = ?, dataCriacao = ?, dataUltimaEdicao = ?, statusRevisao = ?, proximaRevisao = ?, idCaderno = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getConteudo());
            ps.setDate(2, documento.getDataCriacao());
            ps.setDate(3, documento.getDataUltimaEdicao());
            ps.setString(4, documento.getStatusRevisao().name());
            ps.setDate(5, documento.getProximaRevisao());
            ps.setInt(6, documento.getCaderno().getId());
            ps.setInt(7, documento.getCaderno().getId());

            ps.executeUpdate();
            System.out.println("Documento atualizado");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar documento: " +e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM documentos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Documento deletado");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar documento: " +e.getMessage());
        }
    }

}
