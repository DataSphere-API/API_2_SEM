package org.datasphere.service;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        List<TopicoModel> topicosPendentes = new LinkedList<>(topicos);
        Map<TopicoModel, Integer> progressoAulas = new HashMap<>();

        for (LocalDate dataAtual = semestreModel.getDiaInicio(); !dataAtual.isAfter(semestreModel.getDiaFim()); dataAtual = dataAtual.plusDays(1)) {
            if (topicosPendentes.isEmpty()) break;

            LocalDate finalDia = dataAtual;
            DiaModel diaModelAtual = semestreModel.getDiasList().stream()
                    .filter(d -> d.getData().equals(finalDia))
                    .findFirst()
                    .orElse(null);

            if (diaModelAtual == null) continue;

            boolean diaJaTemAulaNormal = false;
            TopicoModel provaDoDia = null;

            for (AulaModel aula : aulas) {
                if (topicosPendentes.isEmpty()) break;

                if (dataAtual.getDayOfWeek() == aula.getDiaDaSemana()) {
                    TopicoModel topicoEscolhido = null;

                    if (provaDoDia != null) {
                        topicoEscolhido = provaDoDia;
                    } else {
                        for (TopicoModel topicoCandidato : topicosPendentes) {
                            if (topicoCandidato.getProva()) {
                                if (!diaJaTemAulaNormal && diaModelAtual.getDisponivelParaProva()) {
                                    topicoEscolhido = topicoCandidato;
                                    provaDoDia = topicoCandidato;
                                    break;
                                }
                            } else {
                                topicoEscolhido = topicoCandidato;
                                break;
                            }
                        }
                    }

                    if (topicoEscolhido != null) {
                        AulaPlanejada aulaComData = new AulaPlanejada();
                        aulaComData.setAulaModel(aula);
                        aulaComData.setDiaModel(diaModelAtual);
                        aulaComData.setTopicoModel(topicoEscolhido);

                        planejamentoAulas.add(aulaComData);
                        aulaPlanejadaDAO.salvar(aulaComData);

                        if (!topicoEscolhido.getProva()) {
                            diaJaTemAulaNormal = true; //

                            int aulasJaDadas = progressoAulas.getOrDefault(topicoEscolhido, 0) + 1;
                            progressoAulas.put(topicoEscolhido, aulasJaDadas);

                            if (aulasJaDadas >= topicoEscolhido.getAulasNecessarias()) {
                                topicosPendentes.remove(topicoEscolhido);
                            }
                        }
                    }
                }
            }

            if (provaDoDia != null) {
                topicosPendentes.remove(provaDoDia);
            }
        }
        return planejamentoAulas;
    }


}
