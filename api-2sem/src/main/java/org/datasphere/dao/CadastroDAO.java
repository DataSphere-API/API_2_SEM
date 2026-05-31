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

    public void salvarDia(DiaModel dia) {
        String sql = "INSERT INTO dia (data, disponivel_prova, titulo, descricao) VALUES (?,?,?,?) " +
                "ON CONFLICT (data) DO UPDATE SET titulo = EXCLUDED.titulo, descricao = EXCLUDED.descricao";
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(dia.getData()));
            ps.setBoolean(2, dia.isDisponivelParaProva());
            ps.setString(3, dia.getTitulo());
            ps.setString(4, dia.getDescricao());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DiaModel> listarDatas() {
        String sql = "SELECT data, disponivel_prova, titulo, descricao FROM dia WHERE titulo IS NOT NULL ORDER BY data";
        List<DiaModel> dias = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaModel dia = new DiaModel();
                dia.setData(rs.getDate("data").toLocalDate());
                dia.setDisponivelParaProva(rs.getBoolean("disponivel_prova"));
                dia.setTitulo(rs.getString("titulo"));
                dia.setDescricao(rs.getString("descricao"));
                dias.add(dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dias;
    }
}