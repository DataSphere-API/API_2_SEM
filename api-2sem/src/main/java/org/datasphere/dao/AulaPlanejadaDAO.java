package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.AulaPlanejada;
import org.datasphere.model.DiaModel;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AulaPlanejadaDAO implements IDAO<AulaPlanejada> {

    @Override
    public void salvar(AulaPlanejada aulaPlanejada){
        String sql = "INSERT INTO aula_planejada (dia_da_semana, horario, data, id_topico) VALUES (?,?,?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,aulaPlanejada.getAulaModel().getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
            ps.setTime(2, Time.valueOf(aulaPlanejada.getAulaModel().getHoraInicio()));
            ps.setDate(3,Date.valueOf(aulaPlanejada.getDiaModel().getData()));
            ps.setLong(4,aulaPlanejada.getTopicoModel().getId());

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
                aula.setDiaDaSemana(DayOfWeek.valueOf(rs.getString("dia_da_semana")));
                aula.setHoraInicio(rs.getTime("horario").toLocalTime());

                DiaModel dia = new DiaModel();
                dia.setData(rs.getDate("data").toLocalDate());

                TopicoModel topico = new TopicoModel();
                topico.setId(rs.getLong("id_topico"));

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

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
