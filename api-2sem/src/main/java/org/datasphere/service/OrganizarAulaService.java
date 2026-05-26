package org.datasphere.service;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrganizarAulaService {

    private IDAO<AulaPlanejada> aulaPlanejadaDAO = new AulaPlanejadaDAO();

    public IDAO<AulaPlanejada> getAulaPlanejadaDAO() {
        return aulaPlanejadaDAO;
    }

    public void setAulaPlanejadaDAO(IDAO<AulaPlanejada> aulaPlanejadaDAO) {
        this.aulaPlanejadaDAO = aulaPlanejadaDAO;
    }

    public List<AulaPlanejada> organizarAulas(List<TopicoModel> topicos, List<AulaModel> aulas, SemestreModel semestreModel, int cargaHorariaAlvo) {
        List<AulaPlanejada> planejamentoAulas = new LinkedList<>();

        Map<TopicoModel, Integer> cotaAulas = new LinkedHashMap<>();
        int totalAlocado = 0;

        for (TopicoModel t : topicos) {
            if (t.isProva()) {
                cotaAulas.put(t, 1);
            } else {
                cotaAulas.put(t, t.getAulasMinimas());
                totalAlocado += t.getAulasMinimas();
            }
        }

        boolean adicionouNaRodada = true;
        while (totalAlocado < cargaHorariaAlvo && adicionouNaRodada) {
            adicionouNaRodada = false;
            for (TopicoModel t : topicos) {
                if (!t.isProva()) {
                    int cotaAtual = cotaAulas.get(t);
                    if (cotaAtual < t.getAulasMaximas() && totalAlocado < cargaHorariaAlvo) {
                        cotaAulas.put(t, cotaAtual + 1);
                        totalAlocado++;
                        adicionouNaRodada = true;
                    }
                }
            }
        }

        List<TopicoModel> filaCompleta = new LinkedList<>();
        for (TopicoModel t : topicos) {
            if (t.isProva()) {
                filaCompleta.add(t);
            } else {
                int qtdAulasDesteTopico = cotaAulas.get(t);
                for (int i = 0; i < qtdAulasDesteTopico; i++) {
                    filaCompleta.add(t);
                }
            }
        }

        for (LocalDate dataAtual = semestreModel.getDiaInicio(); !dataAtual.isAfter(semestreModel.getDiaFim()); dataAtual = dataAtual.plusDays(1)) {

            LocalDate finalDia = dataAtual;
            DiaModel diaModelAtual = semestreModel.getDiasList().stream()
                    .filter(d -> d.getData().equals(finalDia))
                    .findFirst()
                    .orElse(null);

            if (diaModelAtual == null) continue;

            List<AulaModel> slotsDoDia = aulas.stream()
                    .filter(a -> a.getDiaDaSemana() == finalDia.getDayOfWeek())
                    .collect(Collectors.toList());

            if (slotsDoDia.isEmpty()) continue;

            TopicoModel provaDesteDia = null;

            for (int slotIndex = 0; slotIndex < slotsDoDia.size(); slotIndex++) {

                if (filaCompleta.isEmpty() && provaDesteDia == null) break;

                AulaModel slotAula = slotsDoDia.get(slotIndex);
                TopicoModel topicoEscolhido = null;

                if (provaDesteDia != null) {
                    topicoEscolhido = provaDesteDia;
                } else {
                    TopicoModel proximo = filaCompleta.get(0);

                    if (proximo.isProva()) {
                        boolean diaLivreParaProva = diaModelAtual.getDisponivelParaProva();
                        boolean ehPrimeiroSlot = (slotIndex == 0);

                        if (diaLivreParaProva && ehPrimeiroSlot) {
                            provaDesteDia = filaCompleta.remove(0);
                            topicoEscolhido = provaDesteDia;
                        } else {
                            for (int i = 1; i < filaCompleta.size(); i++) {
                                if (!filaCompleta.get(i).isProva()) {
                                    topicoEscolhido = filaCompleta.remove(i);
                                    break;
                                }
                            }
                        }
                    } else {
                        topicoEscolhido = filaCompleta.remove(0);
                    }
                }

                if (topicoEscolhido != null) {
                    AulaPlanejada ap = new AulaPlanejada();
                    ap.setAulaModel(slotAula);
                    ap.setDiaModel(diaModelAtual);
                    ap.setTopicoModel(topicoEscolhido);

                    planejamentoAulas.add(ap);
                    aulaPlanejadaDAO.salvar(ap);
                }
            }
        }
        return planejamentoAulas;
    }
}