package com.revisaoWeb.dao;

import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.model.Status;
import com.revisaoWeb.dto.MenuDataDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import java.util.Collections;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentoDao {

    public void criar(Documento documento) {
        String sql = "INSERT INTO documentos (titulo, conteudo, dataUltimaEdicao, dataProximaRevisao, nivelRevisao, statusRevisao, idCadernoFK) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getTitulo());
            ps.setString(2, documento.getConteudo());
            ps.setDate(3, documento.getDataUltimaEdicao());
            ps.setDate(4, documento.getDataProximaRevisao());
            ps.setInt(5, documento.getNivelRevisao());
            ps.setString(6, documento.getStatusRevisao().name()); //.name() puxa em string a informação
            ps.setInt(7, documento.getCaderno().getIdCaderno());

            ps.executeUpdate();
            System.out.println("Documento criado.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar Documento: " + e.getMessage());
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

                caderno.setIdCaderno(rs.getInt("idCadernoFK"));

                documento.setTitulo(rs.getString("titulo"));
                documento.setIdDocumento(rs.getInt("idDocumento"));
                documento.setConteudo(rs.getString("conteudo"));
                documento.setDataCriacao(rs.getDate("dataCriacao"));
                documento.setDataUltimaEdicao(rs.getDate("dataUltimaEdicao"));
                documento.setDataProximaRevisao(rs.getDate("dataProximaRevisao"));
                documento.setNivelRevisao(rs.getInt("nivelRevisao"));
                documento.setStatusRevisao(Status.valueOf(rs.getString("statusRevisao"))); // 'Contrário' para status
                documento.setCaderno(caderno);

                listaDocumentos.add(documento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar documentos: " + e.getMessage());
        }

        System.out.println("Documento listado.");
        return listaDocumentos;
    }

    public Documento listarId(int id) {
        String sql = "SELECT * FROM documentos WHERE idDocumento = ?";
        Documento doc;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doc = new Documento();
                Caderno cad = new Caderno();

                cad.setIdCaderno(rs.getInt("idCadernoFK"));

                doc.setTitulo(rs.getString("titulo"));
                doc.setIdDocumento(rs.getInt("idDocumento"));
                doc.setConteudo(rs.getString("conteudo"));
                doc.setDataCriacao(rs.getDate("dataCriacao"));
                doc.setDataUltimaEdicao(rs.getDate("dataUltimaEdicao"));
                doc.setDataProximaRevisao(rs.getDate("dataProximaRevisao"));
                doc.setNivelRevisao(rs.getInt("nivelRevisao"));
                doc.setStatusRevisao(Status.valueOf(rs.getString("statusRevisao")));
                doc.setCaderno(cad);
            } else {
                doc = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar documento: " + e.getMessage());
        }

        return doc;
    }

    public void atualizar(int id, Documento documento) {
        String sql = "UPDATE documentos SET titulo = ?, previa = ?, conteudo = ?, dataProximaRevisao = ?, nivelRevisao = ?, statusRevisao = ?, idCadernoFK = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getTitulo());
            ps.setString(2, documento.getPrevia());
            ps.setString(3, documento.getConteudo());
            ps.setDate(4, documento.getDataProximaRevisao());
            ps.setInt(5, documento.getNivelRevisao());
            ps.setString(6, documento.getStatusRevisao().name());
            ps.setInt(7, documento.getCaderno().getIdCaderno());
            ps.setInt(8, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado." + id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar documento: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM documentos WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Documento deletado.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar documento: " + e.getMessage());
        }
    }

    public List<Documento> listarDocumentosUsuario(List<Integer> idCadernos) {
        List<Documento> documentosUsuario = new ArrayList<>();

        if (idCadernos == null || idCadernos.isEmpty()) {
            return documentosUsuario;
        }

        int qntIdCadernos = idCadernos.size();
        List<String> prePH = Collections.nCopies(qntIdCadernos, "?");
        String placeholder = String.join(", ", prePH);

        String sql = "SELECT * FROM documentos WHERE idCadernoFK IN (" + placeholder + ")";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            for (int i = 1; i <= qntIdCadernos; i++) {
                ps.setInt(i, idCadernos.get(i - 1));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Documento documento = new Documento();
                Caderno caderno = new Caderno();

                caderno.setIdCaderno(rs.getInt("idCadernoFK"));

                documento.setIdDocumento(rs.getInt("idDocumento"));
                documento.setTitulo(rs.getString("titulo"));
                documento.setConteudo(rs.getString("conteudo"));
                documento.setDataCriacao(rs.getDate("dataCriacao"));
                documento.setDataUltimaEdicao(rs.getDate("dataUltimaEdicao"));
                documento.setDataProximaRevisao(rs.getDate("dataProximaRevisao"));
                documento.setNivelRevisao(rs.getInt("nivelRevisao"));
                documento.setStatusRevisao(Status.valueOf(rs.getString("statusRevisao")));
                documento.setCaderno(caderno);

                documentosUsuario.add(documento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar documento > caderno > usuario." + e.getMessage());
        }
        return documentosUsuario;
    }
}

/*
    // ==============================   TERMINAR DEPOIS   ==============================
    public List<MenuDataDto> listarDataUsuario(int id) {
        List<MenuDataDto> data = new ArrayList<>();

        // VERIFICAR SE A QUERY FUNCIONA
        String sql = "SELECT * FROM cadernos AS c INNER JOIN documentos AS d ON c.idCaderno = d.idCadernoFK INNER JOIN tag_doc AS td ON td.idDocumentoFK = d.idDocumento INNER JOIN tags AS t ON t.idTag = td.idTagFK WHERE idUsuarioFK = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuDataDto x = new MenuDataDto();

                x.setIdUsuario(rs.getInt(''));
                x.setUsuario(rs.getString(''));

                x.setIdTag(rs.getInt(''));
                x.setTag(rs.getString(''));

                x.setIdCaderno(rs.getInt(''));
                x.setCaderno(rs.getString(''));
                x.setIdUsuarioFKCad(rs.getInt(''));

                x.setIdDocumento(rs.getInt(''));
                x.setTitulo(rs.getString(''));
                x.setPrevia(rs.getString(''));
                x.setDataUltimaEdicao(rs.get(''));
                x.setDataProximaRevisao(rs.get(''));
                x.setNivelRevisao(rs.getInt(''));
                x.setStatusRevisao(rs.get(''));
                x.setIdCadernoFKDoc(rs.getInt(''));

                x.setIdCompartilhamento(rs.getInt(''));
                x.setPermissao(rs.get(''));
                x.setIdUsuarioFKCom(rs.getInt(''));
                x.setIdDocumentoFKCom(rs.getInt(''));

                x.setIdTagDoc(rs.getInt(''));
                x.setIdDocumentoFKTD(rs.getInt(''));
                x.setIdTagFKTD(rs.getInt(''));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todo o documento." +e.getMessage());
        }

        return data;
    }
}
*/