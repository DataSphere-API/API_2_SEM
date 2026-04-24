package org.datasphere.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.datasphere.model.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CadastrarAulaController {

    @FXML
    private TableColumn<TopicoModel, Long> clnIdTopico;

    @FXML
    private CheckBox chkProva;

    @FXML
    private TableColumn<TopicoModel, String> clnTopico;

    @FXML
    private TableColumn<TopicoModel, Integer> clnAulasTopico;

    @FXML
    private Spinner<Integer> spnrQtdAulasTopico;

    @FXML
    private TableView<TopicoModel> tblTopicoAdicionado;

    @FXML
    private TextField txtFldTituloTopico;

    @FXML
    private CheckBox chkSegunda,chkTerca, chkQuarta, chkQuinta, chkSexta;

    @FXML
    private CheckBox chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205;

    @FXML
    private CheckBox chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205;

    @FXML
    private CheckBox chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205;

    @FXML
    private CheckBox chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205;

    @FXML
    private CheckBox chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205;

    @FXML
    private TableColumn<AulaPlanejada, String> clnAulasPlanejamento;

    @FXML
    private TableColumn<AulaPlanejada, String> clnDataPlanejamento;

    @FXML
    private TableColumn<AulaPlanejada, String> clnTopicoPlanejamento;

    @FXML
    private TableView<AulaPlanejada> tblPlanejamentoAulas;

    private ObservableList<TopicoModel> obsListTopicos;

    private ObservableList<AulaPlanejada> obsListAulasPlanejadas;

    private Integer contadorID = 1;

    private SemestreModel semestre = new SemestreModel(LocalDate.now(), LocalDate.now().plusMonths(6));

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spnrQtdAulasTopico.setValueFactory(valueFactory);

        criarSemestreComDias();

        obsListTopicos = FXCollections.observableArrayList();
        tblTopicoAdicionado.setItems(obsListTopicos);

        clnTopico.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnTopico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clnAulasTopico.setCellValueFactory(new PropertyValueFactory<>("aulasNecessarias"));

        configurarTabelaTopicos();
    }

    private void configurarTabelaTopicos(){

        clnIdTopico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getId())
        );

        clnTopico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo())
        );

        clnAulasTopico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getAulasNecessarias())
        );
    }

    @FXML
    private TopicoModel cadastrarTopico() {
        String titulo = txtFldTituloTopico.getText();
        Integer aulas = spnrQtdAulasTopico.getValue();

        if (titulo != null && !titulo.isEmpty()) {
            TopicoModel novoTopico = new TopicoModel(titulo, aulas);

            novoTopico.setId(Long.valueOf(contadorID++));

            obsListTopicos.add(novoTopico);

            txtFldTituloTopico.clear();
            return novoTopico;
        }
        return null;
    }

    public void criarSemestreComDias(){
        for (LocalDate dia = semestre.getDiaInicio(); !dia.isAfter(semestre.getDiaFim()); dia = dia.plusDays(1)){
            if (!dia.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !dia.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                semestre.adicionarDias(new DiaModel(LocalDate.of(dia.getYear(),dia.getMonth(),dia.getDayOfMonth()), true));
            }
        }
    }

    public List<AulaModel> lerDiaHorarioAula(){
        List<AulaModel> diasDeAula = new LinkedList<>();
        List<LocalTime> tempos = List.of(
                LocalTime.of(18,45),
                LocalTime.of(19,35),
                LocalTime.of(20,25),
                LocalTime.of(21,15),
                LocalTime.of(22,05));

        if(chkSegunda.isSelected()){
            List<CheckBox> horarios = List.of(chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205);

            for (int i=0; i<horarios.size(); i++){
                if(horarios.get(i).isSelected()){
                    diasDeAula.add(new AulaModel(DayOfWeek.MONDAY, tempos.get(i), tempos.get(i+1)));
                }
            }
        }

        if(chkTerca.isSelected()){
            List<CheckBox> horarios = List.of(chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205);

            for (int i=0; i<horarios.size(); i++){
                if(horarios.get(i).isSelected()){
                    diasDeAula.add(new AulaModel(DayOfWeek.TUESDAY, tempos.get(i), tempos.get(i+1)));
                }
            }
        }

        if(chkQuarta.isSelected()){
            List<CheckBox> horarios = List.of(chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205);

            for (int i=0; i<horarios.size(); i++){
                if(horarios.get(i).isSelected()){
                    diasDeAula.add(new AulaModel(DayOfWeek.WEDNESDAY, tempos.get(i), tempos.get(i+1)));
                }
            }
        }

        if(chkQuinta.isSelected()){
            List<CheckBox> horarios = List.of(chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205);

            for (int i=0; i<horarios.size(); i++){
                if(horarios.get(i).isSelected()){
                    diasDeAula.add(new AulaModel(DayOfWeek.THURSDAY, tempos.get(i), tempos.get(i+1)));
                }
            }
        }

        if(chkSexta.isSelected()){
            List<CheckBox> horarios = List.of(chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205);

            for (int i=0; i<horarios.size(); i++){
                if(horarios.get(i).isSelected()){
                    diasDeAula.add(new AulaModel(DayOfWeek.FRIDAY, tempos.get(i), tempos.get(i+1)));
                }
            }
        }
        return diasDeAula;
    }

    public List<AulaPlanejada> organizarAulas(List<TopicoModel> topicos,List<AulaModel> aulas, SemestreModel semestreModel) {
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

    private void adicionarTopicoLista(){
        List<AulaPlanejada> aulasPlanejadas = organizarAulas(obsListTopicos, lerDiaHorarioAula(), semestre);


            clnAulasPlanejamento.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty((cellData.getValue().getAulaModel().getDiaDaSemana().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")) + " "
                            + (cellData.getValue().getAulaModel().getHoraInicio()+ " - "))
                            + (cellData.getValue().getAulaModel().getHoraFim()))

            );

            clnTopicoPlanejamento.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTopicoModel().getTitulo())
            );

            clnDataPlanejamento.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty((cellData.getValue().getDiaModel().getData().format(fmt)))
            );

        tblPlanejamentoAulas.setItems(FXCollections.observableArrayList(aulasPlanejadas));
    }

    @FXML
    private void gerarPlanejamentoPlanilha(ActionEvent event){
        adicionarTopicoLista();
    }

}
