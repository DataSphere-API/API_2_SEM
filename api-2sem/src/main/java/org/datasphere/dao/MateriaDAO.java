package org.datasphere.dao;

import org.datasphere.database.ConexaoDB;
import org.datasphere.model.MateriaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriaDAO {

    public MateriaModel buscarPorEmailProfessor(String email) {
        String sql = "SELECT * FROM materia WHERE email_professor = ?";
        MateriaModel materia = null;

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materia = new MateriaModel();
                materia.setSigla(rs.getString("sigla"));
                materia.setTitulo(rs.getString("titulo"));
                materia.setCargaHoraria(rs.getInt("carga_horaria"));
                materia.setEmailProfessor(rs.getString("email_professor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }
}