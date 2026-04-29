package org.datasphere.service;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class OrganizarAulaService {

    private IDAO<AulaPlanejada> aulaPlanejadaDAO = new AulaPlanejadaDAO();

    public IDAO<AulaPlanejada> getAulaPlanejadaDAO(){
        return aulaPlanejadaDAO;
    }

    public void setAulaPlanejadaDAO(IDAO<AulaPlanejada> aulaPlanejadaDAO){
        this.aulaPlanejadaDAO = aulaPlanejadaDAO;
    }

    public List<AulaPlanejada> organizarAulas(List<TopicoModel> topicos, List<AulaModel> aulas, SemestreModel semestreModel) {
        List<AulaPlanejada> planejamentoAulas = new LinkedList<>();

        int topicoAtualIndex = 0;
        int aulasNoTopicoAtual = 0;

        for (LocalDate dia = semestreModel.getDiaInicio(); !dia.isAfter(semestreModel.getDiaFim()); dia = dia.plusDays(1)) {
            for (AulaModel aula : aulas) {

                boolean diaDaSemanaCorreto = dia.getDayOfWeek() == aula.getDiaDaSemana();
                LocalDate finalDia = dia;
                boolean diaLetivo = semestreModel.getDiasList().stream()
                        .anyMatch(d -> d.getData().equals(finalDia));

                if (diaDaSemanaCorreto && topicoAtualIndex < topicos.size()) {

                    TopicoModel topicoAtual = topicos.get(topicoAtualIndex);

                    AulaPlanejada aulaComData = new AulaPlanejada();
                    aulaComData.setAulaModel(aula);
                    aulaComData.setDiaModel(new DiaModel(dia, true));
                    aulaComData.setTopicoModel(topicoAtual);

                    planejamentoAulas.add(aulaComData);
                    aulaPlanejadaDAO.salvar(aulaComData);

                    aulasNoTopicoAtual++;
                    if (aulasNoTopicoAtual >= topicoAtual.getAulasNecessarias()) {
                        topicoAtualIndex++;
                        aulasNoTopicoAtual = 0;
                    }
                }
            }
        }

        return planejamentoAulas;
    }

}
