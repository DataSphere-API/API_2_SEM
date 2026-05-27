<p align="center">
  <img src="../logo_datasphere.png" width="200"/>
  <h2 align="center"> Critérios de Aceite - Sprint 1 </h2>
</p>


#### Como professor, quero poder validar a carga horária do planejamento feito, para que a carga mínima seja atingida.

<table>
    <tr>
    <td>
        Considerando que o professor visualiza o resumo do planejamento, o sistema deve exibir a carga horária total prevista, comparando com a carga mínima exigida para a disciplina.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que a carga horária total do planejamento está abaixo do mínimo exigido, o sistema deve exibir um alerta informando a diferença entre a carga atual e a carga mínima necessária.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que a carga horária total do planejamento atinge ou supera o mínimo exigido, o sistema deve indicar visualmente que a carga está dentro do esperado, sem exibir alertas de erro.
    </td>
    </tr>
</table>

<br>

#### Como professor, quero ser avisado quando houver tópicos ainda sem data definida ou dias de aula sem conteúdo atribuído, para que eu não entregue um planejamento com lacunas.

<table>
    <tr>
    <td>
        Considerando que o professor possui tópicos do conteúdo programático que ainda não foram associados a nenhuma aula, o sistema deve exibir um aviso listando os tópicos pendentes de atribuição.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o professor possui dias de aula cadastrados sem nenhum conteúdo atribuído, o sistema deve exibir um aviso listando as datas com lacunas de conteúdo.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que todos os tópicos estão atribuídos e todos os dias de aula possuem conteúdo, o sistema não deve exibir nenhum aviso de lacunas, indicando que o planejamento está completo.
    </td>
    </tr>
</table>

<br>

#### Como professor, quero visualizar o planejamento em formato de calendário mensal, para que eu tenha uma visão temporal clara de todas as aulas, avaliações e eventos do semestre.

<table>
    <tr>
    <td>
        Considerando que o professor acessa a visualização do planejamento, o sistema deve exibir um calendário mensal contendo todas as aulas, avaliações e eventos agendados no semestre, diferenciados visualmente por tipo.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o professor navega entre os meses do semestre no calendário, o sistema deve atualizar a exibição mostrando corretamente os eventos correspondentes ao mês selecionado.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o professor seleciona um dia específico no calendário, o sistema deve exibir os detalhes do evento ou aula correspondente àquela data, como título do tópico, tipo de atividade e duração.
    </td>
    </tr>
</table>