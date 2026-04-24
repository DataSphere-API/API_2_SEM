package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.AulaPlanejada;
import org.datasphere.model.DiaModel;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AulaPlanejadaDAO implements IDAO<AulaPlanejada> {

    @Override
    public void salvar(AulaPlanejada aulaPlanejada){
        String sql = "INSERT INTO aulaPlanejada (diaDaSemana, horaInicio, horaFim, data, nomeTopico) VALUES (?,?,?,?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,aulaPlanejada.getAulaModel().getDiaDaSemana().name());
            ps.setTime(2, Time.valueOf(aulaPlanejada.getAulaModel().getHoraInicio()));
            ps.setTime(3, Time.valueOf(aulaPlanejada.getAulaModel().getHoraFim()));
            ps.setDate(4,Date.valueOf(aulaPlanejada.getDiaModel().getData()));
            ps.setString(5,aulaPlanejada.getTopicoModel().getTitulo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AulaPlanejada> listar(){
        String sql = "SELECT * FROM aulaPlanejada ORDER BY data";
        List<AulaPlanejada> aulaPlanejadaList = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                AulaModel aula = new AulaModel();
                aula.setDiaDaSemana(DayOfWeek.valueOf(rs.getString("diaDaSemana")));
                aula.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                aula.setHoraFim(rs.getTime("horaFim").toLocalTime());

                DiaModel dia = new DiaModel();
                dia.setData(rs.getDate("data").toLocalDate());

                TopicoModel topico = new TopicoModel();
                topico.setTitulo(rs.getString("nomeTopico"));

                AulaPlanejada aulaPlanejada = new AulaPlanejada();
                aulaPlanejada.setAulaModel(aula);
                aulaPlanejada.setDiaModel(dia);
                aulaPlanejada.setTopicoModel(topico);

                aulaPlanejadaList.add(aulaPlanejada);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulaPlanejadaList;
    }

    @Override
    public void deletar(AulaPlanejada aulaPlanejada) {
        String sql = "DELETE FROM aula WHERE data = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(aulaPlanejada.getDiaModel().getData()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
