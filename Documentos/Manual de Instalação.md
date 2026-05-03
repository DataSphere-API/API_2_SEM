<p align="center">
  <img src="assets/logo_datasphere.png" width="200"/>
  <h2 align="center"> Datasphere </h2>
</p>


# Manual de Instalação

Este documento descreve como compilar e executar o SIGA\.ME a partir do código-fonte. Destinado a desenvolvedores e responsáveis pela implantação do sistema.

## ⚙️ Pré-requisitos
- Java 11+
- Maven 3.6+
- PostgreSQL 13+

## 📥 1. Clone o repositório
```bash
git clone https://github.com/DataSphere-API/API_2_SEM.git
cd API_2_SEM/
```

## 🗄️ 2. Crie o banco de dados
No PostgreSQL, crie um banco chamado `sigame_db` e execute nele o script `script_sigame.sql`.

Você pode fazer isso pelo **pgAdmin** (Query Tool → cole o conteúdo do script → Executar) ou pela linha de comando, conforme seu sistema operacional.

### Windows
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
psql -U postgres -d sigame_db -f script_sigame.sql
```

### Linux
Abra o psql:
```bash
sudo -u postgres psql
```
Dentro do prompt, crie o banco e saia:
```sql
CREATE DATABASE sigame_db;
\q
```
Execute o script (envie o conteúdo via pipe para evitar problemas de permissão):
```bash
sudo -u postgres psql -d sigame_db -f script-sigame.sql
```

## 🔐 3. Configure as variáveis de ambiente
Na pasta `api-2sem/`, crie um arquivo `.env` com base no `.env.example`:
```env
DB_URL=jdbc:postgresql://localhost:5432/sigame_db
DB_USER=postgres
DB_PASSWORD=sua_senha
DB_DRIVER=org.postgresql.Driver
```

> O arquivo `.env` já está no `.gitignore` e não deve ser versionado.

## 📦 4. Baixe as dependências
```bash
cd api-2sem
mvn clean install
```

## 🚀 5. Execute a aplicação
```bash
mvn javafx:run
```

A janela do SIGA.ME será aberta automaticamente.

---

> Para o uso do sistema após a instalação, consulte o <a href= "Manual do Usuário.md"> Manual do Usuário. </a>