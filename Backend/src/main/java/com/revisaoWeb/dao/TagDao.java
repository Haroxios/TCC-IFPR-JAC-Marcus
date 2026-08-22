package com.revisaoWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.revisaoWeb.config.DatabaseConnection;
import com.revisaoWeb.model.Tag;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class TagDao {

    public void criar(Tag tag) {
        String sql = "INSERT INTO tags (tag) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, tag.getTag());
            ps.executeUpdate();
            System.out.println("Tag criada.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao criar Tag: " +e.getMessage());
        }
    }

    public List<Tag> listar() {
        String sql = "SELECT * FROM tags";
        List<Tag> listaTag = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Tag tag = new Tag();

                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));

                listaTag.add(tag);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Tags: " +e.getMessage());
        }

        return listaTag;
    }

    public Tag listarId(int id) {
        String sql = "SELECT * FROM Tags WHERE id = ?";
        Tag tag;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tag = new Tag();

                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
            }
            else {
                tag = null;
            }
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tag: " +e.getMessage());
        }

        return tag;
    }

    public void atualizar(int id, Tag tag) {
        String sql = "UPDATE tags SET tag = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, tag.getTag());
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Tag atualizada.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tag: " +e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tags WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Tag deletada.");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Tag: " +e.getMessage());
        }
    }
}
