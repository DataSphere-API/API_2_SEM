package org.datasphere.dao;

import org.datasphere.database.ConexaoDB;
import org.datasphere.model.MateriaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public List<MateriaModel> buscarTodasPorEmailProfessor(String email) {
        String sql = "SELECT * FROM materia WHERE email_professor = ?";
        List<MateriaModel> listaMaterias = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MateriaModel materia = new MateriaModel();
                materia.setSigla(rs.getString("sigla"));
                materia.setTitulo(rs.getString("titulo"));
                materia.setCargaHoraria(rs.getInt("carga_horaria"));
                materia.setEmailProfessor(rs.getString("email_professor"));

                listaMaterias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMaterias;
    }
}