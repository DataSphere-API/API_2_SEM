package org.datasphere.dao;

import org.datasphere.database.ConexaoDB;
import org.datasphere.model.ProfessorModel;
import org.datasphere.service.SenhaHashService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public ProfessorModel checarLogin(String email, String senha) {
        String sql = "SELECT nome, email, coordenador FROM professor WHERE EMAIL = ? AND SENHA = ?";
        ProfessorModel professorLogado = null;

        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            //ps.setString(2, SenhaHashService.gerarHashSenha(senha));
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                professorLogado = new ProfessorModel();

                professorLogado.setNome(rs.getString("nome"));
                professorLogado.setEmail(rs.getString("email"));
                professorLogado.setCoordenador(rs.getBoolean("coordenador"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professorLogado;
    }

}