package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.AulaPlanejada;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AulaDAO implements IDAO<AulaModel> {

    @Override
    public void salvar(AulaModel aulaModel){
        String sql = "INSERT INTO aula (dia_da_semana, horario) VALUES (?,?) ON CONFLICT DO NOTHING";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,aulaModel.getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
            ps.setTime(2, Time.valueOf(aulaModel.getHoraInicio()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AulaModel> listar(){
        String sql = "SELECT * FROM aula";
        List<AulaModel> aulaModelList = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                AulaModel aulaModel = new AulaModel();

                aulaModel.setDiaDaSemana(DayOfWeek.valueOf(rs.getString("dia_da_semana")));
                aulaModel.setHoraInicio(rs.getTime("horario").toLocalTime());
                aulaModel.setHoraFim(rs.getTime("horario").toLocalTime().plusMinutes(50));

                aulaModelList.add(aulaModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulaModelList;
    }

    @Override
    public void deletar(AulaModel aula){
        String sql = "DELETE FROM aula WHERE dia_da_semana = ? AND horario = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aula.getDiaDaSemana().name());
            ps.setTime(2, Time.valueOf(aula.getHoraInicio()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
