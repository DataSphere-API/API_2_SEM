# SIGA.ME </a> - Planejamento de Aulas Automatizada 
<p align="center">
  <img src="Documentos/logo_datasphere.png" width="200"/>
</p>
<p align="center">
    <a href ="#desafio"> Desafio</a> |
    <a href ="#solução"> Solução</a> |
    <a href ="#backlog"> Backlog do Produto</a> |   
    <a href ="#sprints"> Cronograma das Sprints</a> |
    <a href ="#documentacao"> Documentação </a> |
    <a href ="#tecnologias"> Tecnologias</a> |
    <a href ="#equipe"> Equipe </a> |
    

</p>

---

> Status do projeto: Em Desenvolvimento


### 🎯 Desafio do Projeto  <a id="desafio"></a>
No começo de cada semestre letivo, os professores precisam lançar seus planos de aula no sistema.
Essa atividade deveria ser simples, contudo a usabilidade do sistema deixa tudo mais difícil.

Os professores devem, para cada disciplina, informar em uma tela todos os dias/horários de aula do semestre e em outra tela separada, devem informar, por dia, qual o conteúdo da aula e a quantidade de aulas do dia.

<b> Exemplo: </b>

Em uma tela seriam lançados os seguintes dias/horários:

- 09/02/2026 20:25-21:15
- 11/02/2026 18:45-19:35
- 12/02/2026 21:25-23:05
- 19/02/2026 21:25-23:05
- 23/02/2026 20:25-21:15

Na tela de conteúdo, ele seria lançado da seguinte forma:
- Recepção dos alunos: 1 aula
- Introdução da disciplina: 1 aula
- Projeto Integrador: 2 aulas
- Sistemas Gerenciadores de Banco de Dado: 2 aulas
- Modelagem de Dados: 1 aula

<b> Com dezenas de aulas por semestre, fica extremamente difícil manter a sincronia das informações. </b>

Os professores também precisam planejar o conteúdo levando em consideração as datas do API, o que torna o processo ainda mais desafiador. 

Além disso, se houver muitos feriados os professores precisam marcar aulas ao sábado para compor a carga horária mínima das disciplinas (40 horas aula para disciplinas com 2 aulas semenais ou 80 horas aula para disciplinas com 4 aulas semanais).

### Solução - SIGA.ME </a>

Um sistema que crie o planejamento automaticamente, realizando o cruzamento automatizado entre os dias letivos e não letivos do calendário acadêmico da FATEC e o conteúdo programático da matéria, levando em conta a quantidade de aulas que cada tópico vai demandar.



---
### 📋 Backlog do Produto <a id="backlog"></a>
<div align="center">
  <table>
    <tr>
      <th> Id </th>
      <th>User Stories</th>
      <th>Prioridade</th>
      <th>Estimativa</th>
    </tr>
    <tr>
      <td align="center"> <b> US01 </b> </td>
      <td> Como professor, quero que as aulas sejam distribuídas baseado nos tópicos do conteúdo nas datas de aula disponíveis, respeitando a quantidade de aulas exigida por cada tópico e a capacidade de cada dia, para que eu não precise fazer esse cruzamento manualmente. </td>
      <td align="center">  ALTA </td>
      <td align="center"> 13 </td>
    </tr>
    <tr>
    <td align="center"> <b> US02 </b> </td>
      <td>Como professor quero poder ver um relatório geral do planejamento de aulas do semestre para que eu possa passá-lo para o SIGA.</td>
      <td align="center">  ALTA </td>
      <td align="center"> 5 </td>
    </tr>
    <tr>
      <td align="center"> <b> US03 </b> </td>
      <td>Como professor, quero que as avaliações sejam agendadas fora de datas restritas (terceira semana de Sprint, apresentações de TG ou dia da feira de soluções), para que não haja esse erro no planejamento.</td>
      <td align="center">  ALTA </td>
      <td align="center"> 13 </td>
    </tr>
    <tr>
      <td align="center"> <b> US04 </b> </td>
      <td>Como professor quero poder cadastrar, editar e remover os dias da semana e os horários de aula de cada semana, para que uma aula não seja agendada fora do horário.</td>
      <td align="center">  ALTA </td>
      <td align="center"> 3 </td>
    </tr>
    <tr>
      <td align="center"> <b> US05 </b> </td>
      <td>Como professor, quero poder cadastrar, editar e remover o conteúdo programático da minha disciplina, para que o planejamento não seja criado sem algum tópico.</td>
      <td align="center">  ALTA </td>
      <td align="center"> 5 </td>
    </tr>
    <tr>
      <td align="center"> <b> US06 </b> </td>
      <td>Como professor, quero poder validar a carga horária do planejamento feito, para que a carga mínima seja atingida.</td>
      <td align="center">  MÉDIA </td>
      <td align="center"> 2 </td>
    </tr>
    <tr>
      <td align="center"> <b> US07 </b> </td>
      <td>Como professor, quero ser avisado quando houver tópicos ainda sem data definida ou dias de aula sem conteúdo atribuído, para que eu não entregue um planejamento com lacunas.</td>
      <td align="center">  MÉDIA </td>
      <td align="center"> 13 </td>
    </tr>
    <tr>
      <td align="center"> <b> US08 </b> </td>
      <td>Como professor, quero visualizar o planejamento em formato de calendário mensal, para que eu tenha uma visão temporal clara de todas as aulas, avaliações e eventos do semestre.</td>
      <td align="center">  BAIXA </td>
      <td align="center"> 8 </td>
    </tr>
  </table>
</div> 

---

### 📆 Cronograma das Sprints <a id="sprints"></a>

<div>
  <table>
    <th> Sprint </th>
    <th> Período </th>
    <th> Status </th>
    <tr>
      <td align="center"> Sprint 1 </td>
      <td align="center"> 16/03 a 05/04 </td>
      <td align="center"> ✅ </td>
    </tr>
    <tr>
      <td align="center"> Sprint 2 </td>
      <td align="center"> 13/04 a 03/05 </td>
      <td align="center"> EM ANDAMENTO </td>
    </tr>
    <tr>
      <td align="center"> Sprint 3 </td>
      <td align="center"> 11/05 a 31/05 </td>
      <td align="center">  </td>
    </tr>
  </table>
</div>

---

### 📄 Documentação <a id="documentacao"></a>

A documentação está disponível na pasta  <a href="/Documentos/"> Documentos</a>.

##### Conteúdo:
- Padrão de Commits
- Estrutura das Branches
- Backlog do projeto
- Documentação por sprint
- Definition of Ready (DoR)
- Definition of Done (DoD)

##### Conteúdo dividido por sprint:
- Backlog da sprint
- Critérios de aceite

---
### 💻 Tecnologias <a id="tecnologias"></a>

-   **Versionamento:** Git
-   **Ferramenta de gestão:** Slack
-   **Linguagem:** Java
-   **Plataforma:** IntelliJ IDEA 

---

### 👥 Equipe
<div align="center" id ="equipe">
  <table>
    <tr>
      <th>Membro</th>
      <th>Função</th>
      <th>Github</th>
      <th>Linkedin</th>
    </tr>
     <tr>
      <td>Vinicius Santos</td>
      <td>Product Owner</td>
      <td><a href="https://github.com/vncssd"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://www.linkedin.com/in/vncssd?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
    <tr>
      <td>Carolina Medeiros</td>
      <td>Scrum Master</td>
      <td><a href="https://github.com/mcarolinamedeiros"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://br.linkedin.com/in/mcarolinamedeiros"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr> 
     <tr>
      <td>Daiane Moura</td>
      <td>Desenvolvedor</td>
      <td><a href="https://github.com/mouradaiane"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://www.linkedin.com/in/daiane-moura-189987106/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
   <tr>
      <td>Ieda Moretini</td>
      <td>Desenvolvedor</td>
      <td><a href="https://github.com/imoretini"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="www.linkedin.com/in/iedamoretini"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
    <tr>
      <td>Lucas Nathan</td>
      <td>Desenvolvedor</td>
      <td><a href="https://github.com/Consolucas"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://www.linkedin.com/in/lucasconsolo/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
    <tr>
      <td>Luiz Felipe Andrade</td>
      <td>Desenvolvedor</td>
      <td><a href="https://github.com/luiz-andrade1"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://www.linkedin.com/in/luiz-felipe-queir%C3%B3s/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
 </table>
</div>

