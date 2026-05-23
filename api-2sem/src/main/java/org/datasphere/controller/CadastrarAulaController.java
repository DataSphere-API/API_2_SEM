package org.datasphere.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.datasphere.dao.AulaPlanejadaDAO;
import org.datasphere.dao.TopicoDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.service.AulaService;
import org.datasphere.service.OrganizarAulaService;
import org.datasphere.service.SemestreService;
import org.datasphere.service.TopicoService;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

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
    private CheckBox chkSegunda, chkTerca, chkQuarta, chkQuinta, chkSexta;

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

    private IDAO<AulaPlanejada> aulaPlanejadaDAO = new AulaPlanejadaDAO();

    private IDAO<TopicoModel> topicoDAO = new TopicoDAO();

    private TopicoService topicoService = new TopicoService();

    private AulaService aulaService = new AulaService();

    private SemestreService semestreService = new SemestreService();

    private OrganizarAulaService organizarAulaService = new OrganizarAulaService();

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spnrQtdAulasTopico.setValueFactory(valueFactory);

        semestreService.criarSemestreComDias();
        criarSemanaSprint();

        obsListTopicos = FXCollections.observableArrayList();
        tblTopicoAdicionado.setItems(obsListTopicos);

        clnTopico.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnTopico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clnAulasTopico.setCellValueFactory(new PropertyValueFactory<>("aulasNecessarias"));

        configurarTabelaTopicos();

        chkProva.selectedProperty().addListener((observable, oldValue, newValue) -> {
            spnrQtdAulasTopico.setDisable(newValue);
            spnrQtdAulasTopico.getValueFactory().setValue(0);
        });

        List<CheckBox> horariosSegunda = List.of(chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205);
        List<CheckBox> horariosTerca = List.of(chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205);
        List<CheckBox> horariosQuarta = List.of(chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205);
        List<CheckBox> horariosQuinta = List.of(chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205);
        List<CheckBox> horariosSexta = List.of(chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205);

        Map<CheckBox, List<CheckBox>> mapaDiasHorarios = new HashMap<>();

        mapaDiasHorarios.put(chkSegunda, horariosSegunda);
        mapaDiasHorarios.put(chkTerca, horariosTerca);
        mapaDiasHorarios.put(chkQuarta, horariosQuarta);
        mapaDiasHorarios.put(chkQuinta, horariosQuinta);
        mapaDiasHorarios.put(chkSexta, horariosSexta);

        for (Map.Entry<CheckBox, List<CheckBox>> entrada : mapaDiasHorarios.entrySet()) {

            CheckBox chkDiaPrincipal = entrada.getKey();
            List<CheckBox> listaDeHorarios = entrada.getValue();

            listaDeHorarios.forEach(chk -> chk.setDisable(true));

            chkDiaPrincipal.setOnAction(e -> {

                boolean diaEstaMarcado = chkDiaPrincipal.isSelected();

                listaDeHorarios.forEach(chkHorario -> {
                    chkHorario.setDisable(!diaEstaMarcado);
                    if (!diaEstaMarcado) {
                        chkHorario.setSelected(false);
                    }
                });
            });
        }
    }

    private void configurarTabelaTopicos() {
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
    private void cadastrarTopico() {
        String titulo = txtFldTituloTopico.getText();
        if (titulo != null && !titulo.isEmpty()) {
            TopicoModel novoTopico = topicoService.cadastrar(titulo, spnrQtdAulasTopico.getValue(), chkProva.isSelected());
            obsListTopicos.add(novoTopico);
            txtFldTituloTopico.clear();
        }
    }

    public void criarSemanaSprint() {
        for (int i = 6; i < 14; i++) {
            semestreService.getSemestre().getDiasList().get(i).setDisponivelParaProva(false);
        }
    }

    public List<AulaModel> lerDiaHorarioAula() {
        List<AulaModel> diasDeAula = new LinkedList<>();

        List<LocalTime> tempos = List.of(
                LocalTime.of(18, 45),
                LocalTime.of(19, 35),
                LocalTime.of(20, 25),
                LocalTime.of(21, 15),
                LocalTime.of(22, 05),
                LocalTime.of(23, 05));

        // Cada entrada associa o checkbox do dia (DayOfWeek, lista de horários)
        Map<CheckBox, DayOfWeek> diasDaSemana = new LinkedHashMap<>();
        diasDaSemana.put(chkSegunda, DayOfWeek.MONDAY);
        diasDaSemana.put(chkTerca,   DayOfWeek.TUESDAY);
        diasDaSemana.put(chkQuarta,  DayOfWeek.WEDNESDAY);
        diasDaSemana.put(chkQuinta,  DayOfWeek.THURSDAY);
        diasDaSemana.put(chkSexta,   DayOfWeek.FRIDAY);

        Map<CheckBox, List<CheckBox>> horariosDosDias = new LinkedHashMap<>();
        horariosDosDias.put(chkSegunda, List.of(chkSeg1845, chkSeg1935, chkSeg2025, chkSeg2115, chkSeg2205));
        horariosDosDias.put(chkTerca,   List.of(chkTer1845, chkTer1935, chkTer2025, chkTer2115, chkTer2205));
        horariosDosDias.put(chkQuarta,  List.of(chkQua1845, chkQua1935, chkQua2025, chkQua2115, chkQua2205));
        horariosDosDias.put(chkQuinta,  List.of(chkQui1845, chkQui1935, chkQui2025, chkQui2115, chkQui2205));
        horariosDosDias.put(chkSexta,   List.of(chkSex1845, chkSex1935, chkSex2025, chkSex2115, chkSex2205));

        for (Map.Entry<CheckBox, DayOfWeek> entrada : diasDaSemana.entrySet()) {
            CheckBox chkDia = entrada.getKey();
            DayOfWeek diaDaSemana = entrada.getValue();

            if (!chkDia.isSelected()) continue;

            List<CheckBox> horarios = horariosDosDias.get(chkDia);
            for (int i = 0; i < horarios.size(); i++) {
                if (horarios.get(i).isSelected()) {
                    diasDeAula.add(new AulaModel(diaDaSemana, tempos.get(i), tempos.get(i + 1)));
                }
            }
        }

        aulaService.salvarAulas(diasDeAula);

        return diasDeAula;
    }

    private void adicionarTopicoLista() {
        List<AulaPlanejada> aulasPlanejadas = organizarAulaService.organizarAulas(obsListTopicos, lerDiaHorarioAula(), semestreService.getSemestre());

        clnAulasPlanejamento.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty((cellData.getValue().getAulaModel().getDiaDaSemana().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")) + " "
                        + (cellData.getValue().getAulaModel().getHoraInicio() + " - "))
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
    private void gerarPlanejamentoPlanilha(ActionEvent event) {
        adicionarTopicoLista();
    }
}