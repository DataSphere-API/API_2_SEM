package org.datasphere.model;

import org.datasphere.database.ConexaoDB;
import org.datasphere.service.SenhaHashService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAO {

    public boolean checarLogin(String email, String senha){
        String sql = "SELECT email, senha FROM professor WHERE EMAIL = ? AND SENHA = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, SenhaHashService.gerarHashSenha(senha));

            if (ps.executeUpdate(sql) == 1){
                return true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
