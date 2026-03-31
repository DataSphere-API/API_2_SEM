<p align="center">
  <img src="../logo_datasphere.png" width="200"/>
  <h2 align="center"> Critérios de aceite -  Sprint 1 </h2>
</p>


 #### Como professor, quero que as aulas sejam distribuídas baseado nos tópicos do conteúdo nas datas de aula disponíveis, respeitando a quantidade de aulas exigida por cada tópico e a capacidade de cada dia, para que eu não precise fazer esse cruzamento manualmente. 

<table>
    <tr>
    <td>
         Considerando que o professor cadastrou os tópicos e os dias letivos, quando solicitar a distribuição automática, então cada tópico deve ser alocado em datas que comportem a quantidade de aulas necessárias.
    </td>
    </tr>
    <tr>
    <td>
        Considerando que um tópico exige 2 aulas, quando o dia disponível tiver capacidade para apenas 1 aula, então esse tópico não deve ser alocado nesse dia
    </td>
    </tr>
    <tr>
    <td>
        Considerando que todos os tópicos foram distribuídos, quando o professor visualizar o planejamento, então nenhuma data deve ter mais aulas do que sua capacidade permite
    </td>
    </tr>
    <tr>
    <td>
        Considerando que não há datas suficientes para todos os tópicos, quando o professor solicitar a distribuição, então ele deve ser avisado sobre quais tópicos ficaram sem data
    </td>
    </tr>
</table>

<br>

 #### Como professor quero poder ver um relatório geral do planejamento de aulas do semestre para que eu possa passá-lo para o SIGA.

<table>
    <tr>
    <td>
         Considerando que o professor finalizou o planejamento, quando solicitar o relatório, então ele deve conter todas as datas de aula com seus respectivos tópicos
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o relatório foi gerado, então ele deve indicar o tipo de cada encontro (aula, avaliação, reposição)
    </td>
    </tr>
    <tr>
    <td>
        Considerando que o relatório foi gerado, então ele deve estar organizado em ordem cronológica
    </td>
    </tr>
</table>