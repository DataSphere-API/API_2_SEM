package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.AulaPlanejada;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO implements IDAO<AulaModel> {

    @Override
    public void salvar(AulaModel aulaModel){
        String sql = "INSERT INTO aula (diaDaSemana, horaInicio, horaFim) VALUES (?,?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,aulaModel.getDiaDaSemana().name());
            ps.setTime(2, Time.valueOf(aulaModel.getHoraInicio()));
            ps.setTime(3, Time.valueOf(aulaModel.getHoraFim()));

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

                aulaModel.setDiaDaSemana(DayOfWeek.valueOf(rs.getString(1)));
                aulaModel.setHoraInicio(rs.getTime(1).toLocalTime());
                aulaModel.setHoraFim(rs.getTime(1).toLocalTime());

                aulaModelList.add(aulaModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulaModelList;
    }

    @Override
    public void deletar(AulaModel aula){
        String sql = "DELETE FROM aula WHERE diaDaSemana = ? AND horaInicio = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aula.getDiaDaSemana().name());
            ps.setTime(2, Time.valueOf(aula.getHoraInicio()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
