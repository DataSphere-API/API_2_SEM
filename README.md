# SIGA\.ME - Planejamento de Aulas Automatizado
<p align="center">
  <img src="Documentos/logo_datasphere.png" width="200"/>
</p>
<p align="center">
    <a href = #sobre>
    <a href ="#desafio"> Desafio</a> |
    <a href ="#solução"> Solução</a> |
    <a href ="#backlog"> Backlog do Produto</a> |   
    <a href ="#sprints"> Cronograma das Sprints</a> |
    <a href ="#estrutura"> Estrutura do Projeto</a> |
    <a href ="#documentacao"> Documentação </a> |
    <a href ="#tecnologias"> Tecnologias</a> |
    <a href ="#equipe"> Equipe </a> |
    

</p>

### Sobre o Projeto <a id="sobre"></a>

SIGA\.ME é um sistema desenvolvido por alunos do 2° semestre do curso de Banco de Dados da FATEC como  PROJETO INTEGRADOR, com o objetivo de automatizar o planejamento de aulas dos professores no início de cada semestre letivo. A solução elimina o trabalho manual de cruzar o calendário acadêmico com o conteúdo programático das disciplinas, reduzindo erros e economizando tempo dos docentes.

---

> Status do projeto: Em Desenvolvimento


### 🎯 Desafio  <a id="desafio"></a>
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
- Sistemas Gerenciadores de Banco de Dados: 2 aulas
- Modelagem de Dados: 1 aula

<b> Com dezenas de aulas por semestre, fica extremamente difícil manter a sincronia das informações. </b>

Além disso, se houver muitos feriados os professores precisam marcar aulas aos sábados para compor a carga horária mínima das disciplinas (40 horas-aula para disciplinas com 2 aulas semanais ou 80 horas aula para disciplinas com 4 aulas semanais).

Os professores também precisam planejar o conteúdo considerando outros eventos do calendário acadêmico, como a Feira de Soluções, a 3ª semana de sprint e a apresentação de TG, datas em que não é permitido o agendamento de avaliações, o que torna o processo ainda mais desafiador. 


### Solução - SIGA\.ME

O SIGA\.ME é um sistema que gera o planejamento de aulas de forma automatizada, fazendo o cruzamento inteligente entre os dias letivos e não letivos do calendário acadêmico da FATEC e o conteúdo programático de cada disciplina.

A partir do conteúdo cadastrado pelo professor e da quantidade de aulas exigida por cada tópico, o sistema distribui as aulas ao longo do semestre respeitando a carga horária mínima da disciplina, evita o agendamento de avaliações em datas restritas (como a 3ª semana de Sprint, a Feira de Soluções e a apresentação de TG) e sinaliza eventuais lacunas no planejamento, como tópicos sem data ou dias de aula sem conteúdo atribuído. 

Dessa forma, o professor passa a contar com um planejamento consistente, validado e pronto para ser lançado no SIGA.

##### CLIQUE <a href="https://youtu.be/DfhsHqrduig"> AQUI </a> PARA VER O FUNCIONAMENTO DA FERRAMENTA

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
      <td align="center"> <a href="/Documentos/Sprint1/"> Sprint 1 </a> </td>
      <td align="center"> 16/03 a 05/04 </td>
      <td align="center"> ✅ </td>
    </tr>
    <tr>
      <td align="center"> <a href="/Documentos/Sprint2/"> Sprint 2 </a> </td>
      <td align="center"> 13/04 a 03/05 </td>
      <td align="center"> ✅ </td>
    </tr>
    <tr>
      <td align="center"> Sprint 3 </td>
      <td align="center"> 11/05 a 31/05 </td>
      <td align="center"> </td>
    </tr>
  </table>
</div>

---

## 📁 Estrutura do Projeto <a id = "estrutura"></a>

```
API_2_SEM/
├── Documentos/                          # Documentação do projeto
│   ├── Sprint1/                         # Backlog, critérios de aceite e rascunhos
│   ├── Sprint2/                         # Backlog, critérios de aceite e rascunhos
|   ├── DER-API.png
│   ├── Definition of Done.md
│   ├── Definition of Ready.md
│   ├── Diretrizes de Permanência.md
│   ├── Estratégia de Branch.md
│   ├── Padrão de Commits.md
│   ├── Manual de Instalação.md
│   ├── Manual do Usuário.md
│   └── logo_datasphere.png
│
├── api-2sem/                            # Aplicação JavaFX
│   ├── src/main/java/org/datasphere/
│   │   ├── App.java                     # Ponto de entrada (main)
│   │   ├── controller/                  # Controllers do JavaFX
│   │   │   └── CadastrarAulaController.java
│   │   ├── service/                     # Regras de negócio
│   │   │   ├── OrganizarAulaService.java
│   │   │   └── SemestreService.java
│   │   ├── dao/                         # Acesso ao banco de dados
│   │   │   ├── interfaces/
│   │   │   │   └── IDAO.java
│   │   │   ├── AulaDAO.java
│   │   │   ├── AulaPlanejadaDAO.java
│   │   │   ├── DiaDAO.java
│   │   │   └── TopicoDAO.java
│   │   ├── model/                       # Entidades
│   │   │   ├── AulaModel.java
│   │   │   ├── AulaPlanejada.java
│   │   │   ├── DiaModel.java
│   │   │   ├── SemestreModel.java
│   │   │   └── TopicoModel.java
│   │   └── database/                    # Configuração de conexão com banco de dados
│   │       └── ConexaoDB.java
│   ├── src/main/resources/
│   │   ├── org/datasphere/
│   │   │   └── cadastro-aula.fxml       # Definição da tela principal
│   │   └── static/                      # Imagens e recursos visuais
│   ├── .env.example                     # Modelo do arquivo de ambiente
│   ├── pom.xml                          # Dependências e configuração Maven
│   └── script_sigame.sql                # Script de criação das tabelas
│
├── README.md
└── .gitignore
```

---

## 🏗️ Arquitetura

A aplicação segue o padrão **MVC com camada de serviço e DAO**, organizando o código em quatro responsabilidades bem definidas:

| Camada | Pasta | Responsabilidade |
|---|---|---|
| **Apresentação (View + Controller)** | `controller/` + `resources/org/datasphere/(FXML) `| Receber as ações do usuário e atualizar a interface |
| **Negócio (Services)** | `service/` | Regras de distribuição de aulas e cálculo do semestre |
| **Persistência (DAO)** | `dao/` | Operações de leitura e escrita no PostgreSQL |
| **Domínio (Model)** | `model/` | Classes que representam as entidades do sistema |

---

### 📄 Documentação <a id="documentacao"></a>

A documentação está disponível na pasta  <a href="/Documentos/"> Documentos</a>.

##### Conteúdo:
- <a href="/Documentos/Padrão de Commits.md"> Padrão de Commits </a>
- <a href="/Documentos/Estratégia de Branch.md"> Estratégia de Branches </a>
- <a href="/Documentos/Manual do Usuário.md"> Manual do usuário </a>
- <a href="/Documentos/Manual de Instalação.md"> Manual de Instalação </a>
- <a href="/Documentos/"> Documentação por sprint </a>
- <a href="/Documentos/Definition of Ready.md"> Definition of Ready (DoR) </a>
- <a href="/Documentos/Definition of Done.md"> Definition of Done (DoD) </a>

##### Conteúdo dividido por sprint:
- Backlog da sprint
- Critérios de aceite
- Rascunhos das telas

---
### 💻 Tecnologias <a id="tecnologias"></a>

- **Linguagem:** Java <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="20" height="20" alt="Java"/>
- **Build & Dependências:** Maven <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="20" height="20" alt="Maven"/>
- **IDE:** IntelliJ IDEA <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" width="20" height="20" alt="IntelliJ IDEA"/>
- **Banco de Dados:** PostgreSQL <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" width="20" height="20" alt="PostgreSQL"/>
- **Versionamento:** Git <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="20" height="20" alt="Git"/>
- **Gestão & Comunicação:** Slack <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/slack/slack-original.svg" width="20" height="20" alt="Slack"/>
  
---

### 👥 Equipe <a id="equipe"></a>
<div align="center">
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
      <td><a href="https://www.linkedin.com/in/iedamoretini"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
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
    <tr>
      <td>Matheus Quirino</td>
      <td>Desenvolvedor</td>
      <td><a href="https://github.com/matquirin0"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a></td>
      <td><a href="https://www.linkedin.com/in/matheus-pquirino/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a></td>
    </tr>
 </table>
</div>
