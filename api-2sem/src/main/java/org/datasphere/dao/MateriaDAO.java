package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.MateriaModel;

import java.sql.*;
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

    public void salvar(MateriaModel materiaModel) {
        String sql = "INSERT INTO materia (sigla, titulo, carga_horaria, email_professor) VALUES (?,?, ?, ?)";
        try (Connection conn = ConexaoDB.getConexao()) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, materiaModel.getSigla());
            ps.setString(2, materiaModel.getTitulo());
            ps.setInt(3, materiaModel.getCargaHoraria());
            ps.setString(4, materiaModel.getEmailProfessor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

