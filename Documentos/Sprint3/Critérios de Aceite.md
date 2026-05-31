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

#### Como professor, quero poder exportar o planejamento como CSV, para que eu tenha uma visão geral clara de todas as aulas, avaliações e eventos do semestre.

<table>
    <tr>
    <td>
        Considerando que o professor gerou o planejamento, o sistema deve disponibilizar um botão de exportação que, ao ser clicado, abre o seletor de arquivo para que o professor escolha onde salvar o CSV.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o professor confirma o local de salvamento, o sistema deve gerar um arquivo CSV contendo as colunas: DIA DA SEMANA, HORÁRIO, DATA e TÓPICO, com uma linha para cada aula planejada em ordem cronológica.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o planejamento contém aulas de FECHAMENTO geradas automaticamente, o sistema deve incluí-las no CSV normalmente, identificadas com o título "FECHAMENTO".
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o professor tenta exportar sem ter gerado o planejamento, o sistema deve exportar apenas as aulas que estiverem visíveis na tabela no momento do clique, sem gerar erros.
    </td>
    </tr>
</table>