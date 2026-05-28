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

    public MateriaModel buscarPorEmailProfessor(String email) {
        String sql = "SELECT * FROM materia WHERE email_professor = ?";
        MateriaModel materia = null;

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materia = mapearMateria(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }

    /**
     * Lista todas as matérias vinculadas a um professor pelo e-mail.
     * Um professor pode ter mais de uma matéria (ex.: professor substituto ou
     * semestres distintos), por isso retorna uma lista.
     */
    public List<MateriaModel> listarPorEmailProfessor(String email) {
        String sql = "SELECT * FROM materia WHERE email_professor = ?";
        List<MateriaModel> materias = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materias.add(mapearMateria(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    private MateriaModel mapearMateria(ResultSet rs) throws SQLException {
        MateriaModel materia = new MateriaModel();
        materia.setSigla(rs.getString("sigla"));
        materia.setTitulo(rs.getString("titulo"));
        materia.setCargaHoraria(rs.getInt("carga_horaria"));
        materia.setEmailProfessor(rs.getString("email_professor"));
        return materia;
    }
}