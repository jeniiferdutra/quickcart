# 🛒 QuickCart API

> **API RESTful para gerenciamento de e-commerce com controle de estoque em tempo real, cálculo transacional de pedidos e tratamento global de exceções.**

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue?style=flat-square&logo=spring)](https://spring.io/projects/spring-data-jpa)
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)

---

## Sobre o Projeto

O **QuickCart** é uma solução backend para e-commerce desenvolvida com **Java** e **Spring Boot**. A aplicação simula o fluxo real de uma loja virtual, permitindo o gerenciamento do catálogo de produtos e o processamento seguro de pedidos.

O principal diferencial técnico do projeto é a implementação rigorosa de **regras de negócio e integridade de dados**, garantindo que compras só sejam efetuadas se houver saldo em estoque, ajustando o inventário em tempo real e mantendo o histórico financeiro dos itens no momento da venda.

---

## Principais Funcionalidades

- ** Gerenciamento de Produtos (CRUD):**
  - Cadastro de produtos com validações de dados (preço positivo, nome obrigatório).
  - Listagem de catálogo e busca por ID.
  - Atualização de dados e reposição de estoque.

- ** Processamento Transacional de Pedidos:**
  - Criação de pedidos com validação prévia de saldo de estoque para cada item.
  - Baixa automática no estoque de cada produto associado ao pedido.
  - Cálculo dinâmico do valor total acumulado.
  - Snapshot de preço unitário no `ItemPedido` para preservação do histórico de vendas.
  - Controle de concorrência e consistência via `@Transactional` (Rollback automático em falhas).

- ** Tratamento Global de Exceções:**
  - Respostas HTTP amigáveis e padronizadas no formato JSON via `@ControllerAdvice`.
  - Retorno de `HTTP 400 Bad Request` customizado em tentativas de compra sem estoque suficiente.
  - Retorno de `HTTP 404 Not Found` em buscas por IDs inexistentes.

---

## Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Framework:** Spring Boot 3
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (Desenvolvimento) / PostgreSQL (Produção)
- **Validação de Dados:** Bean Validation (`jakarta.validation`)
- **Gestão de Dependências:** Maven

---

## Modelagem de Dados

A arquitetura de banco de dados resolve o relacionamento N:N (Muitos para Muitos) entre **Pedido** e **Produto** utilizando a entidade de ligação **ItemPedido**:
