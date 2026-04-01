package org.datasphere.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.datasphere.App;
import org.datasphere.enums.DiaEnum;
import org.datasphere.model.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CadastrarAulaController {

    @FXML
    private TableColumn<TopicoModel, Long> clnIdTopico;

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
    private CheckBox chkSeg1845, chkSeg1935, chkTer1845, chkTer1935, chkQua1845, chkQua1935, chkQui1845, chkQui1935, chkSex1845, chkSex1935;

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

    private SemestreModel semestre = new SemestreModel(LocalDate.of(2026,04,01), LocalDate.of(2026,04,30));

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spnrQtdAulasTopico.setValueFactory(valueFactory);

        obsListTopicos = FXCollections.observableArrayList();
        tblTopicoAdicionado.setItems(obsListTopicos);

        clnTopico.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnTopico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clnAulasTopico.setCellValueFactory(new PropertyValueFactory<>("aulasNecessarias"));

        configurarTabela();
        criarSemestreComDias();
    }

    private void configurarTabela(){

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

    /**
    ESSE METODO INSTANCIA UM SEMESTRE (QUE COMEÇA NO DIA 01/04 E TERMINA NO DIA 30/04) E CRIA UMA SEMANA LETIVA PARA ESSE MÊS PARA FINS DE APRESENTAÇÃO
     APÓS ISSO, ELE DEVE SER ALTERADO SEM FALTA!!!!
    **/
    public void criarSemestreComDias(){
        SemestreModel semestre = new SemestreModel(LocalDate.of(2026,04,01), LocalDate.of(2026,04,30));
        DiaModel diaLetivo = new DiaModel(LocalDate.of(2026,04,1), DiaEnum.LETIVO);
        DiaModel diaLetivo1 = new DiaModel(LocalDate.of(2026,04,2), DiaEnum.LETIVO);
        DiaModel diaLetivo2 = new DiaModel(LocalDate.of(2026,04,6), DiaEnum.LETIVO);
        DiaModel diaLetivo3 = new DiaModel(LocalDate.of(2026,04,7), DiaEnum.LETIVO);
        DiaModel diaLetivo4 = new DiaModel(LocalDate.of(2026,04,8), DiaEnum.LETIVO);
        DiaModel diaLetivo5 = new DiaModel(LocalDate.of(2026,04,9), DiaEnum.LETIVO);
        DiaModel diaLetivo7 = new DiaModel(LocalDate.of(2026,04,10), DiaEnum.LETIVO);

        DiaModel diaNaoLetivo = new DiaModel(LocalDate.of(2026,04,03), DiaEnum.NAO_LETIVO);
        DiaModel diaNaoLetivo1 = new DiaModel(LocalDate.of(2026,04,04), DiaEnum.NAO_LETIVO);
        DiaModel diaNaoLetivo2 = new DiaModel(LocalDate.of(2026,04,05), DiaEnum.NAO_LETIVO);
    }

    public AulaModel lerDiaHorarioAula(){
        if (chkSegunda.isSelected() && chkSeg1845.isSelected() && chkSeg1935.isSelected()){
            AulaModel aulaSegunda = new AulaModel(DayOfWeek.MONDAY, LocalTime.of(18,45), LocalTime.of(19,35));
            chkSeg1935.setIndeterminate(true);
            chkSeg1935.setIndeterminate(true);
            chkSegunda.setIndeterminate(true);
            return aulaSegunda;
        }
        return null;
    }

    public List<AulaPlanejada> organizarAulas(List<AulaPlanejada> aulas, SemestreModel semestreModel) {
        List<AulaPlanejada> planejamentoAulas = new LinkedList<>();

        for (LocalDate dia = semestreModel.getDiaInicio(); !dia.isAfter(semestreModel.getDiaFim()); dia = dia.plusDays(1)) {
            for (AulaPlanejada aula : aulas) {

                boolean diaDaSemanaCorreto = dia.getDayOfWeek() == aula.getAulaModel().getDiaDaSemana();
                boolean diaLetivo = aula.isDiaLetivo();

                if (diaDaSemanaCorreto && diaLetivo) {
                    AulaPlanejada aulaComData = new AulaPlanejada(
                            aula.getDiaModel(),
                            aula.getTopicoModel(),
                            aula.getAulaModel()
                    );
                    aulaComData.setDiaModel(new DiaModel(dia, DiaEnum.LETIVO));
                    planejamentoAulas.add(aulaComData);
                }
            }
        }

        return planejamentoAulas;
    }








}
