package org.datasphere.dao;

import org.datasphere.database.ConexaoDB;
import org.datasphere.model.ProfessorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {

    public void cadastrar(ProfessorModel professor){
        String sql = "INSERT INTO professor (email, nome, senha) VALUES (?,?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, professor.getEmail());
            ps.setString(2,professor.getNome());
            ps.setString(3,professor.getSenha());

            ps.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}
