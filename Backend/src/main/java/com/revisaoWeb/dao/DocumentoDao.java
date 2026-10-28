package com.revisaoWeb.dao;

import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.model.Status;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.time.LocalDateTime;
import java.time.LocalDate;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentoDao {

    /*

    dá para depois fazer o métoodo dentro do métoodo
    mas não é essencial para funcionar
    pois já funciona sem ele

    private Documento mapearMenuDocumento(ResultSet rs) throws SQLException{
        Documento documento = new Documento();
        Caderno caderno = new Caderno();

        caderno.setIdCaderno(rs.getInt("idCadernoFK"));

        documento.setIdDocumento(rs.getInt("idDocumento"));
        documento.setTitulo(rs.getString("titulo"));
        documento.setPrevia(rs.getString("previa"));
        documento.setConteudo(rs.getString("conteudo"));
        documento.setDataCriacao(rs.getObject("dataCriacao", LocalDateTime.class));
        documento.setDataUltimaEdicao(rs.getObject("dataUltimaEdicao", LocalDateTime.class));
        documento.setDataProximaRevisao(rs.getObject("dataProximaRevisao", LocalDate.class));
        documento.setNivelRevisao(rs.getInt("nivelRevisao"));
        documento.setStatusRevisao(Status.valueOf(rs.getString("statusRevisao")));
        documento.setCaderno(caderno);

        return documento;
    }*/

    // dá para remover a previa já que quando criar o documento ele virá vazio mesmo
    public void criar(Documento documento) {
        String sql = "INSERT INTO documentos (titulo, previa, conteudo, dataProximaRevisao, nivelRevisao, statusRevisao, idCadernoFK) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getTitulo());
            ps.setString(2, documento.getPrevia());
            ps.setString(3,documento.getConteudo());
            ps.setObject(4, documento.getDataProximaRevisao());
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
                documento.setDataCriacao(rs.getObject("dataCriacao", LocalDateTime.class));
                documento.setDataUltimaEdicao(rs.getObject("dataUltimaEdicao", LocalDateTime.class));
                documento.setDataProximaRevisao(rs.getObject("dataProximaRevisao", LocalDate.class));
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
                doc.setDataCriacao(rs.getObject("dataCriacao", LocalDateTime.class));
                doc.setDataUltimaEdicao(rs.getObject("dataUltimaEdicao", LocalDateTime.class));
                doc.setDataProximaRevisao(rs.getObject("dataProximaRevisao", LocalDate.class));
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
        String sql = "UPDATE documentos SET titulo = ?, previa = ?, conteudo = ?, idCadernoFK = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, documento.getTitulo());
            ps.setString(2, documento.getPrevia());
            ps.setString(3, documento.getConteudo());
            ps.setInt(4, documento.getCaderno().getIdCaderno());
            ps.setInt(5, id);

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
                documento.setConteudo(rs.getString("previa"));
                documento.setDataCriacao(rs.getObject("dataCriacao", LocalDateTime.class));
                documento.setDataUltimaEdicao(rs.getObject("dataUltimaEdicao", LocalDateTime.class));
                documento.setDataProximaRevisao(rs.getObject("dataProximaRevisao", LocalDate.class));
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

    public void atualizarRevisao(int id, int nivel, LocalDate data) {
        String sql = "UPDATE documentos SET nivelRevisao = ?, dataProximaRevisao = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {


            ps.setInt(1, nivel);
            ps.setObject(2, data);
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado - atualizarRevisao: " + id);

        } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar - atualizarRevisao: " + e.getMessage());
        }
    }

    public void atualizarData(int id, LocalDate data) {
        String sql = "UPDATE documentos SET dataProximaRevisao = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setObject(1, data);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado - atualizarData: " + id);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar - atualizarData: " + e.getMessage());
        }
    }

    public void atualizarStatus(int id, Status status) {
        String sql = "UPDATE documentos SET statusRevisao = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, String.valueOf(status));
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado - atualizarStatus: " + id);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar - atualizarStatus: " + e.getMessage());
        }
    }

    public void atualizarRevisaoStatus(int id, int nivel, LocalDate data, Status status) {
        String sql = "UPDATE documentos SET nivelRevisao = ?, dataProximaRevisao = ?, statusRevisao = ? WHERE idDocumento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, nivel);
            ps.setObject(2, data);
            ps.setString(3, String.valueOf(status));
            ps.setInt(4, id);

            ps.executeUpdate();
            System.out.println("Documento atualizado - atualizarRevisaStatus: " + id);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar - atualizarRevisaStatus: " + e.getMessage());
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

}
