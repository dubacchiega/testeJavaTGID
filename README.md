# Sistema de Transações Financeiras

Este projeto é uma aplicação Java para gerenciar transações financeiras entre clientes e empresas. O sistema permite a inclusão de novos clientes e empresas, além de realizar operações de depósito e saque.

## Funcionalidades

- **Cadastro de Clientes**: Permite adicionar novos clientes com validação de CPF.
- **Cadastro de Empresas**: Permite adicionar novas empresas com validação de CNPJ.
- **Depósitos**: Permite a realização de depósitos na conta de um cliente, com aplicação de taxas.
- **Saques**: Permite a realização de saques da conta de um cliente, com aplicação de taxas.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **JPA (Java Persistence API)**: Para gerenciamento de entidades e transações com o banco de dados.
- **H2 Database**: Banco de dados em memória para testes.
- **Log4j**: Para logging de informações e erros.

## Estrutura do Projeto

- **`src/`**: Código-fonte do projeto.
  - **`infra/`**: Contém classes de acesso ao banco de dados (DAOs).
  - **`usuarios/`**: Contém as entidades `Cliente` e `Empresa`.
  - **`transacao/`**: Contém as classes para operações de depósito e saque.
  - **`cadastro/`**: Contém classes para inclusão de clientes e empresas.
  - **`Main.java`**: Classe principal para executar operações de depósito e saque.
