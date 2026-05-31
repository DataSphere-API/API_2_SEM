package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.DiaModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiaDAO implements IDAO<DiaModel> {

    @Override
    public void salvar(DiaModel diaModel) {
        String sql = "INSERT INTO dia (data, disponivel_prova, letivo) VALUES (?,?,?) ON CONFLICT (data) DO NOTHING";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(diaModel.getData()));
            ps.setBoolean(2, diaModel.getDisponivelParaProva());
            ps.setBoolean(3, diaModel.isLetivo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaModel> listar() {
        String sql = "SELECT * FROM dia ORDER BY data";
        List<DiaModel> diaModelList = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DiaModel diaModel = new DiaModel();
                diaModel.setData(rs.getDate("data").toLocalDate());
                diaModel.setTitulo(rs.getString("titulo"));
                diaModel.setDescricao(rs.getString("descricao"));
                diaModel.setDisponivelParaProva(rs.getBoolean("disponivel_prova"));
                diaModel.setLetivo(rs.getBoolean("letivo"));
                diaModelList.add(diaModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diaModelList;
    }

    public void salvarOuAtualizar(DiaModel diaModel) {
        String sql = "INSERT INTO dia (data, disponivel_prova, titulo, descricao, letivo) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (data) DO UPDATE SET " +
                "disponivel_prova = EXCLUDED.disponivel_prova, " +
                "titulo = EXCLUDED.titulo, " +
                "descricao = EXCLUDED.descricao, " +
                "letivo = EXCLUDED.letivo";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(diaModel.getData()));
            ps.setBoolean(2, diaModel.getDisponivelParaProva());
            ps.setString(3, diaModel.getTitulo());
            ps.setString(4, diaModel.getDescricao());
            ps.setBoolean(5, diaModel.isLetivo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarDisponibilidade(LocalDate data) {
        String sql = "UPDATE dia SET disponivel_prova = ? WHERE data = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, false);
            ps.setDate(2, Date.valueOf(data));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(DiaModel diaModel){
        String sql = "DELETE FROM dia WHERE data = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(diaModel.getData()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
