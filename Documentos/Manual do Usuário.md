<p align="center">
  <img src="assets/logo_datasphere.png" width="200"/>
  <h2 align="center"> Datasphere </h2>
</p>

# Manual do Usuário

Este documento descreve como utilizar o SIGA.ME no dia a dia. O sistema possui dois perfis de acesso: **Coordenador**, responsável pela configuração do calendário e das disciplinas, e **Professor**, responsável pelo planejamento de aulas de cada disciplina.

> Para instruções de instalação do sistema, consulte o <a href="Manual de Instalação.md">Manual de Instalação.</a>

---

## 🔐 Acesso ao Sistema

Ao abrir o SIGA.ME, você verá a tela de login.

1. Informe seu **e-mail** e **senha** cadastrados;
2. Clique em **Entrar**.

O sistema identifica automaticamente o seu perfil:
- **Coordenadores** são redirecionados para a tela Home, onde escolhem o perfil de acesso;
- **Professores** são redirecionados diretamente para a tela de planejamento.

---

## 🗂️ Perfil: Coordenador

O coordenador é o administrador do sistema. Ele configura o calendário acadêmico e as disciplinas antes que os professores possam gerar seus planejamentos.

### Aba Calendário

#### Adicionar Período Letivo

1. Selecione a **Data Inicial** e a **Data Final** do semestre;
2. Clique em **Adicionar Período Letivo**.

O sistema cadastra automaticamente todos os dias úteis (segunda a sexta) do intervalo como dias letivos disponíveis para planejamento.

> O período letivo deve ser configurado **antes** de qualquer outra ação. Os professores só conseguem gerar planejamentos após essa etapa.

#### Adicionar Período Sprint

1. Selecione a **Data Inicial** e a **Data Final** do período de sprint;
2. Clique em **Adicionar Período Sprint**.

Os dias do período de sprint serão marcados como **restritos para provas** — o sistema não irá agendar avaliações nesse intervalo.

> O período de sprint pode ter no máximo **7 dias**.

#### Adicionar Data Específica (Feriados e Eventos)

Para cadastrar feriados, recessos ou qualquer data especial:

1. Selecione a data no campo **Data / Feriado**;
2. Preencha o **Título** (ex.: `Feriado Nacional`, `Feira de Soluções`);
3. Preencha a **Descrição** com detalhes adicionais (opcional);
4. Clique em **Salvar Data**.

A data aparecerá na tabela **Datas Cadastradas** à direita, com o contador atualizado.

---

### Aba Disciplinas

#### Cadastrar Disciplina

1. Preencha o **Nome da Disciplina** (ex.: `Estrutura de Dados`);
2. Preencha o **ID da Disciplina** (sigla, ex.: `ED`);
3. Selecione a **Carga Horária**: 40 horas ou 80 horas;
4. Selecione o **Professor** responsável no ComboBox;
5. Clique em **Cadastrar Disciplina**.

A disciplina aparecerá na tabela à direita e ficará disponível para o professor selecionado.

#### Filtrar Disciplinas

Use o campo de busca acima da tabela para filtrar por nome ou sigla. O filtro é aplicado em tempo real conforme você digita.

---

## 🖥️ Perfil: Professor

### Visão Geral

A tela do professor é dividida em quatro áreas:
- **Seleção de Matéria** — ComboBox no topo para escolher qual disciplina planejar;
- **Dias de aula** — onde você marca os dias e horários da disciplina;
- **Conteúdo Programático** — onde você cadastra os tópicos do plano de ensino;
- **Planejamento de Aulas** — tabela com o resultado gerado automaticamente.

---

### 1. Selecione a Matéria

No ComboBox do topo da tela, selecione a disciplina que deseja planejar.

Ao selecionar:
- Os contadores de **Carga Horária**, **Aulas Planejadas** e **Horas Faltantes** são atualizados;
- As aulas planejadas anteriores (se houver) são carregadas na tabela automaticamente.

> Se você leciona mais de uma disciplina, repita o processo para cada uma.

---

### 2. Marque os dias e horários de aula

No painel **Dias de aula**:
1. Marque o(s) dia(s) da semana em que tem aula da disciplina (Segunda a Sexta);
2. Para cada dia, marque os horários correspondentes (18:45, 19:35, 20:25, 21:15 ou 22:05).

> Cada horário marcado representa uma aula de 50 minutos.

---

### 3. Cadastre o conteúdo programático

No painel **Conteúdo Programático**, para cada tópico do plano de ensino:

1. Digite o nome do tópico (ex.: `Modelagem de Dados`);
2. Selecione a quantidade mínima e máxima de aulas que ele consome (1 a 10);
3. Marque **Prova** se for uma avaliação — os campos de quantidade serão desabilitados automaticamente;
4. Clique em **Cadastrar Conteúdo**.

O tópico aparecerá na tabela com o intervalo de aulas ou a marcação **PROVA**. Repita para cada item do plano.

> A ordem em que você cadastra os tópicos é a ordem em que eles serão distribuídos nas aulas. Cadastre na sequência didática desejada.

#### Excluir tópico

- Selecione um tópico na tabela e clique em **🗑** para remover apenas ele;
- Clique em **🗑** sem nada selecionado para limpar todos os tópicos.

---

### 4. Gere o planejamento

Com dias, horários e tópicos cadastrados, clique em **Gerar Planejamento**.

A tabela lateral será preenchida automaticamente com:
- **Aula** — dia da semana e horário;
- **Tópico** — qual conteúdo será dado naquela aula;
- **Data** — a data específica do encontro.

Os contadores de **Aulas Planejadas** e **Horas Faltantes** são atualizados conforme o resultado.

> O sistema respeita automaticamente os períodos de sprint cadastrados pelo coordenador — avaliações não serão agendadas nessas datas.

---

### 5. Exporte o planejamento

Clique em **📥** para exportar o planejamento como arquivo **CSV**.

O arquivo gerado contém as colunas:
- `DIA DA SEMANA`
- `HORÁRIO`
- `DATA`
- `TÓPICO`

Salve o arquivo no local desejado e use-o como referência para o lançamento no SIGA institucional.

---

## 💡 Dicas

- Cadastre **todos** os tópicos antes de gerar o planejamento. A geração distribui apenas os tópicos cadastrados no momento do clique.
- Se precisar ajustar algo após gerar o planejamento, refaça os cadastros e clique em **Gerar Planejamento** novamente.
- O coordenador precisa ter configurado o **período letivo** antes que o planejamento possa ser gerado.
- Ao trocar de matéria no ComboBox, a tabela de planejamento é atualizada automaticamente com os dados daquela disciplina.