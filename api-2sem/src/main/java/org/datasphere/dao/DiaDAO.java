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
    public void salvar(DiaModel diaModel){
        String sql = "INSERT INTO dia (data, disponivel_prova) VALUES (?,?) ON CONFLICT (data) DO NOTHING";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(diaModel.getData()));
            ps.setBoolean(2, diaModel.getDisponivelParaProva());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaModel> listar(){
        String sql = "SELECT * FROM dia";
        List<DiaModel> diaModelList = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                DiaModel diaModel = new DiaModel();

                diaModel.setData(rs.getDate("data").toLocalDate());
                diaModel.setDisponivelParaProva(rs.getBoolean("disponivel_prova"));

                diaModelList.add(diaModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diaModelList;
    }

    public void atualizarDisponibilidade(DiaModel diaModel) {
        String sql = "UPDATE dia SET disponivel_prova = ? WHERE data = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, diaModel.getDisponivelParaProva());
            ps.setDate(2, Date.valueOf(diaModel.getData()));
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
