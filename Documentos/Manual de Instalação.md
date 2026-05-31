<p align="center">
  <img src="assets/logo_datasphere.png" width="200"/>
  <h2 align="center"> Datasphere </h2>
</p>


# Manual de Instalação

Este documento descreve como compilar e executar o SIGA\.ME a partir do código-fonte. Destinado a desenvolvedores e responsáveis pela implantação do sistema.

## ⚙️ Pré-requisitos
- Java 11+
- Maven 3.6+
- Conta no [Supabase](https://supabase.com) (gratuita)

## 📥 1. Clone o repositório
```bash
git clone https://github.com/DataSphere-API/API_2_SEM.git
cd API_2_SEM/
```

## 🗄️ 2. Configure o banco de dados no Supabase

O SIGA.ME utiliza o **Supabase** como banco de dados em nuvem. Não é necessário instalar o PostgreSQL localmente.

1. Acesse [supabase.com](https://supabase.com) e crie um projeto;
2. No painel do projeto, vá em **SQL Editor**;
3. Cole o conteúdo do arquivo `script-sigame.sql` (na raiz do repositório) e execute.

As tabelas serão criadas automaticamente no banco.

## 🔐 3. Configure as variáveis de ambiente

Na pasta `api-2sem/`, crie um arquivo `.env` com base no `.env.example`:

```env
DB_URL=jdbc:postgresql://db.<PROJECT_REF>.supabase.co:5432/postgres?sslmode=require
DB_USER=postgres
DB_PASSWORD=sua_senha_do_supabase
DB_DRIVER=org.postgresql.Driver
```

Substitua `<PROJECT_REF>` pelo código do seu projeto, encontrado em **Project Settings → General** no painel do Supabase. A senha pode ser redefinida em **Project Settings → Database → Reset database password**.

> O arquivo `.env` já está no `.gitignore` e **não deve ser versionado**. Compartilhe as credenciais apenas com membros autorizados da equipe.

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