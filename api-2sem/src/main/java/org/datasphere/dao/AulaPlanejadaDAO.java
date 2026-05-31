package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.AulaModel;
import org.datasphere.model.AulaPlanejada;
import org.datasphere.model.DiaModel;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AulaPlanejadaDAO implements IDAO<AulaPlanejada> {

    @Override
    public void salvar(AulaPlanejada aulaPlanejada) {
        String sql = "INSERT INTO aula_planejada (dia_da_semana, horario, data, id_topico) VALUES (?,?,?,?)";
        try (Connection conn = ConexaoDB.getConexao()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aulaPlanejada.getAulaModel().getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
            ps.setTime(2, Time.valueOf(aulaPlanejada.getAulaModel().getHoraInicio()));
            ps.setDate(3, Date.valueOf(aulaPlanejada.getDiaModel().getData()));
            ps.setLong(4, aulaPlanejada.getTopicoModel().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AulaPlanejada> listar() {
        String sql = "SELECT ap.dia_da_semana, ap.horario, ap.data, " +
                "t.id, t.titulo, t.aulas_minimas, t.aulas_maximas, t.prova " +
                "FROM aula_planejada ap " +
                "JOIN topico t ON ap.id_topico = t.id " +
                "ORDER BY ap.data";
        return executarConsulta(sql, null);
    }

    public List<AulaPlanejada> listarPorSiglaMateria(String siglaMateria) {
        String sql = "SELECT ap.dia_da_semana, ap.horario, ap.data, " +
                "t.id, t.titulo, t.aulas_minimas, t.aulas_maximas, t.prova " +
                "FROM aula_planejada ap " +
                "JOIN topico t ON ap.id_topico = t.id " +
                "WHERE t.sigla_materia = ? " +
                "ORDER BY ap.data";
        return executarConsulta(sql, siglaMateria);
    }

    private List<AulaPlanejada> executarConsulta(String sql, String parametro) {
        List<AulaPlanejada> aulaPlanejadaList = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (parametro != null) {
                ps.setString(1, parametro);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AulaModel aula = new AulaModel();
                String diaNome = rs.getString("dia_da_semana");
                aula.setDiaDaSemana(converterNomeDia(diaNome));
                LocalTime horaInicio = rs.getTime("horario").toLocalTime();
                aula.setHoraInicio(horaInicio);
                aula.setHoraFim(horaInicio.plusMinutes(50));

                DiaModel dia = new DiaModel();
                dia.setData(rs.getDate("data").toLocalDate());
                dia.setDisponivelParaProva(true);

                TopicoModel topico = new TopicoModel();
                topico.setId(rs.getLong("id"));
                topico.setTitulo(rs.getString("titulo"));
                topico.setAulasMinimas(rs.getInt("aulas_minimas"));
                topico.setAulasMaximas(rs.getInt("aulas_maximas"));
                topico.setProva(rs.getBoolean("prova"));

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

    private DayOfWeek converterNomeDia(String nomeDia) {
        if (nomeDia == null) return DayOfWeek.MONDAY;
        switch (nomeDia.toLowerCase().trim()) {
            case "segunda-feira": return DayOfWeek.MONDAY;
            case "terça-feira":
            case "terca-feira":   return DayOfWeek.TUESDAY;
            case "quarta-feira":  return DayOfWeek.WEDNESDAY;
            case "quinta-feira":  return DayOfWeek.THURSDAY;
            case "sexta-feira":   return DayOfWeek.FRIDAY;
            case "sábado":
            case "sabado":        return DayOfWeek.SATURDAY;
            case "domingo":       return DayOfWeek.SUNDAY;
            default:
                try { return DayOfWeek.valueOf(nomeDia.toUpperCase()); }
                catch (Exception e) { return DayOfWeek.MONDAY; }
        }
    }

    @Override
    public void deletar(AulaPlanejada aulaPlanejada) {
        String sql = "DELETE FROM aula_planejada WHERE data = ?";
        try (Connection conn = ConexaoDB.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(aulaPlanejada.getDiaModel().getData()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}