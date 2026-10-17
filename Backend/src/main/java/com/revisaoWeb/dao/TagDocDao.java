package com.revisaoWeb.dao;

import com.revisaoWeb.model.TagDoc;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Tag;
import com.revisaoWeb.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class TagDocDao {

    public void criar(TagDoc td) {
        String sql = "INSERT INTO tag_doc (idDocumentoFK, idTagFK) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, td.getDocumento().getIdDocumento());
            ps.setInt(2, td.getTag().getIdTag());

            ps.executeUpdate();
            System.out.println("TagDoc criada.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao criar TagDoc: " +e.getMessage());
        }
    }

    public List<TagDoc> listar() {
        String sql = "SELECT * FROM tag_doc";
        List<TagDoc> listaTD = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                TagDoc td = new TagDoc();
                Documento doc = new Documento();
                Tag tag = new Tag();

                doc.setIdDocumento(rs.getInt("idDocumentoFK"));
                tag.setIdTag(rs.getInt("idTagFK"));

                td.setIdTagDoc(rs.getInt("idTagDoc"));
                td.setDocumento(doc);
                td.setTag(tag);

                listaTD.add(td);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar TagsDocs:" +e.getMessage());
        }

        System.out.println("TagsDocs listado.");
        return listaTD;
    }

    public TagDoc listarId(int id) {
        String sql = "SELECT * FROM tag_doc WHERE idTagDoc = ?";
        TagDoc tagDoc;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tagDoc = new TagDoc();
                Documento doc = new Documento();
                Tag tag = new Tag();

                doc.setIdDocumento(rs.getInt("idDocumentoFK"));
                tag.setIdTag(rs.getInt("idTagFK"));

                tagDoc.setIdTagDoc(rs.getInt("idTagDoc"));
                tagDoc.setDocumento(doc);
                tagDoc.setTag(tag);
            }
            else {
                tagDoc = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar TagDoc: " +e.getMessage());
        }

        return tagDoc;
    }

    public void atualizar(int id, TagDoc td) {
        String sql = "UPDATE tag_doc SET idDocumentoFK = ?, idTagFK = ? WHERE idTagDoc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, td.getDocumento().getIdDocumento());
            ps.setInt(2, td.getTag().getIdTag());
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("TagDoc atualizado.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar TagDoc: " +e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tag_doc WHERE idTagDoc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("TagDoc deletado.");

        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar TagDoc: " +e.getMessage());
        }

    }
}
