package org.datasphere.service;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
<<<<<<< HEAD
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
=======
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
>>>>>>> cf428fd39198417f8ce644a231c8e41200afcdac

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

<<<<<<< HEAD
    public List<AulaPlanejada> completarHoras(List<AulaPlanejada> planejamentoAtual, List<AulaModel> aulas, SemestreModel semestreModel, int cargaHorariaAlvo) {
        List<AulaPlanejada> novasAulas = new ArrayList<>();
        int horasAgendadas = planejamentoAtual.size();

        if (horasAgendadas >= cargaHorariaAlvo) return novasAulas;

        TopicoModel fechamento = new TopicoModel();
        fechamento.setTitulo("FECHAMENTO");
        fechamento.setProva(false);

        // ETAPA 1 — slots vazios nos dias já existentes no semestre
        for (LocalDate dataAtual = semestreModel.getDiaInicio(); !dataAtual.isAfter(semestreModel.getDiaFim()) && horasAgendadas < cargaHorariaAlvo; dataAtual = dataAtual.plusDays(1)) {
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

            long slotsJaUsados = planejamentoAtual.stream()
                    .filter(ap -> ap.getDiaModel().getData().equals(finalDia))
                    .count();

            slotsJaUsados += novasAulas.stream()
                    .filter(ap -> ap.getDiaModel().getData().equals(finalDia))
                    .count();

            for (int i = (int) slotsJaUsados; i < slotsDoDia.size() && horasAgendadas < cargaHorariaAlvo; i++) {
                AulaPlanejada ap = new AulaPlanejada();
                ap.setAulaModel(slotsDoDia.get(i));
                ap.setDiaModel(diaModelAtual);
                ap.setTopicoModel(fechamento);
                novasAulas.add(ap);
                horasAgendadas++;
            }
        }

        // ETAPA 2 — sábados do semestre, do último para o primeiro
        if (horasAgendadas < cargaHorariaAlvo) {
            List<LocalTime> temposSabado = List.of(
                    LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0),
                    LocalTime.of(11, 0), LocalTime.of(12, 0)
            );

            List<LocalDate> sabados = new ArrayList<>();
            for (LocalDate d = semestreModel.getDiaInicio(); !d.isAfter(semestreModel.getDiaFim()); d = d.plusDays(1)) {
                if (d.getDayOfWeek() == DayOfWeek.SATURDAY) sabados.add(d);
            }
            Collections.reverse(sabados);

            for (LocalDate sabado : sabados) {
                if (horasAgendadas >= cargaHorariaAlvo) break;

                DiaModel diaSabado = new DiaModel(sabado, true);

                for (int i = 0; i < temposSabado.size() && horasAgendadas < cargaHorariaAlvo; i++) {
                    LocalTime inicio = temposSabado.get(i);
                    LocalTime fim = inicio.plusMinutes(50);

                    AulaModel aulaExtra = new AulaModel(DayOfWeek.SATURDAY, inicio, fim);

                    AulaPlanejada ap = new AulaPlanejada();
                    ap.setAulaModel(aulaExtra);
                    ap.setDiaModel(diaSabado);
                    ap.setTopicoModel(fechamento);
                    novasAulas.add(ap);
                    horasAgendadas++;
                }
            }
        }

        return novasAulas;
    }

    public static void exportarPlanejamento (LinkedList<AulaPlanejada> planejamentoAulas, Stage stage){

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Exportar Planejamento em CSV");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Arquivos CSV (*.csv)", "*.csv")
        );

        fileChooser.setInitialFileName("aula_planejada.csv");

        File arquivoSelecionado = fileChooser.showSaveDialog(stage);

        if (arquivoSelecionado != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSelecionado))) {

                writer.write("DIA DA SEMANA;HORÁRIO;DATA;TÓPICO");
                writer.newLine();

                for (AulaPlanejada aulaPlanejada : planejamentoAulas ) {

                    String linha = String.format("%s;%tT;%tF;%s",
                            aulaPlanejada.getAulaModel().getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")),
                            Time.valueOf(aulaPlanejada.getAulaModel().getHoraInicio()),
                            Date.valueOf(aulaPlanejada.getDiaModel().getData()),
                            aulaPlanejada.getTopicoModel().getTitulo());

                    writer.write(linha);
                    writer.newLine();
                }

            } catch (IOException e) {
                System.err.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
            }
        }
    }
=======

>>>>>>> cf428fd39198417f8ce644a231c8e41200afcdac
}
