package org.datasphere.dao;

import org.datasphere.database.ConexaoDB;
import org.datasphere.model.DiaModel;
import org.datasphere.model.ProfessorModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    public void cadastrar(ProfessorModel professor) {
        String sql = "INSERT INTO professor (email, nome, senha) VALUES (?,?,?)";
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, professor.getEmail());
            ps.setString(2, professor.getNome());
            ps.setString(3, professor.getSenha());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProfessorModel> listarProfessores() {
        String sql = "SELECT * FROM professor";
        List<ProfessorModel> professorModelList = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProfessorModel professorModel = new ProfessorModel();
                professorModel.setEmail(rs.getString("email"));
                professorModel.setNome(rs.getString("nome"));
                professorModelList.add(professorModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professorModelList;
    }

}