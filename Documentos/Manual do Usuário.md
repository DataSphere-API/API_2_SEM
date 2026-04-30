<p align="center">
  <img src="logo_datasphere.png" width="200"/>
  <h2 align="center"> Datasphere </h2>
</p>


# Manual do Usuário

## ▶️ Como Executar

### Pré-requisitos
- Java 11+
- Maven 3.6+
- PostgreSQL 13+

### 1. Clone o repositório
```bash
git clone https://github.com/DataSphere-API/API_2_SEM.git

cd API_2_SEM/api-2sem
```

### 2. Crie o banco de dados
No PostgreSQL, crie um banco chamado `sigame_db` e execute nele o script `script-sigame.sql`.

Você pode fazer isso pelo **pgAdmin** (Query Tool → cole o conteúdo do script → Executar) ou pela linha de comando, conforme seu sistema operacional.

#### Windows
Abra o psql:
```bash
psql -U postgres
```
Dentro do prompt, crie o banco e saia:
```sql
CREATE DATABASE sigame_db;
\q
```
Execute o script:
```bash
psql -U postgres -d sigame_db -f script-sigame.sql
```

#### Linux
Abra o psql:
```bash
sudo -u postgres psql
```
Dentro do prompt, crie o banco e saia:
```sql
CREATE DATABASE sigame_db;
\q
```
Execute o script:
```bash
sudo -u postgres psql -d sigame_db -f script-sigame.sql
```

### 3. Configure as variáveis de ambiente
Na pasta `api-2sem/`, crie um arquivo `.env` com base no `.env.example`:
```env
DB_URL=jdbc:postgresql://localhost:5432/sigame_db
DB_USER=postgres
DB_PASSWORD=sua_senha
DB_DRIVER=org.postgresql.Driver
```

> O arquivo `.env` já está no `.gitignore` e não deve ser versionado.

### 4. Baixe as dependências
```bash
cd api_2_sem
mvn clean install
```

### 5. Execute a aplicação
```bash
mvn javafx:run
```

A janela do SIGA.ME será aberta automaticamente.

---

## 🖥️ Uso da Aplicação

A aplicação é dividida em quatro áreas: **Dias de aula**, **Conteúdo Programático**, botão **Gerar Planejamento** e **Planejamento de Aulas**.

### 1. Marcar dias e horários
No painel **Dias de aula**, marque o(s) dia(s) da semana em que tem aula da disciplina e os horários correspondentes (18:45, 19:35, 20:25, 21:15 ou 22:05). Cada horário marcado representa uma aula de 50 minutos.

### 2. Cadastrar o conteúdo programático
No painel **Conteúdo Programático**:
1. Digite o nome do tópico;
2. Selecione a quantidade de aulas (1 a 10);
3. Marque **Prova** se for uma avaliação;
4. Clique em **Cadastrar Conteúdo**.

A ordem de cadastro define a ordem de distribuição dos tópicos.

### 3. Gerar o planejamento
Clique em **Gerar Planejamento**. A tabela inferior será preenchida com a distribuição automática contendo dia da semana, horário, tópico e data de cada aula do semestre.
