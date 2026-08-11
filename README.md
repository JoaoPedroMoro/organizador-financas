# Organizador de Finanças

Aplicação para gerenciamento de finanças pessoais, permitindo registrar receitas e despesas, organizar movimentações por categorias e acompanhar a situação financeira.

O projeto está sendo desenvolvido como uma aplicação full stack utilizando **Spring Boot, React e Flutter**.

## Objetivo

O objetivo da aplicação é oferecer uma forma simples de registrar e visualizar movimentações financeiras pessoais, permitindo que o usuário acompanhe seus gastos e estabeleça limites de orçamento.

## Tecnologias

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Frontend Web

* React
* JavaScript
* HTML
* CSS

### Aplicação Mobile

* Flutter
* Dart

### Ferramentas

* Git
* GitHub
* Postman
* Docker

## Estrutura do projeto

```text
organizador-financas/
│
├── backend/
│   └── src/
│       └── main/
│           └── java/
│               └── br/com/organizador_financas/
│                   ├── controller/
│                   ├── entity/
│                   ├── exception/
│                   ├── repository/
│                   └── service/
│
├── frontend/       # Aplicação web desenvolvida com React
├── mobile/         # Aplicação mobile desenvolvida com Flutter
│
├── .gitignore
└── README.md
```

## Backend

O backend é uma API REST desenvolvida com Spring Boot e utiliza Spring Data JPA para persistência dos dados no PostgreSQL.

A aplicação possui separação de responsabilidades entre as camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
PostgreSQL
```

### Categorias

O primeiro recurso implementado no backend é o gerenciamento de categorias.

A entidade `Categoria` possui atualmente:

* `id`
* `nome`

O CRUD de categorias já está implementado:

| Método | Endpoint           | Descrição                   |
| ------ | ------------------ | --------------------------- |
| GET    | `/categorias`      | Lista todas as categorias   |
| GET    | `/categorias/{id}` | Busca uma categoria pelo ID |
| POST   | `/categorias`      | Cria uma nova categoria     |
| PUT    | `/categorias/{id}` | Atualiza uma categoria      |
| DELETE | `/categorias/{id}` | Remove uma categoria        |

### Tratamento de erros

A API possui tratamento global de exceções para recursos não encontrados.

Por exemplo, ao solicitar uma categoria inexistente:

```text
GET /categorias/999
```

a API retorna:

```json
{
    "status": 404,
    "error": "Not Found",
    "message": "Categoria não encontrada com o ID: 999"
}
```

## Banco de dados

O projeto utiliza PostgreSQL como banco de dados.

Durante o desenvolvimento, o PostgreSQL utilizado pela aplicação é executado em um container Docker.

A configuração local utiliza:

```text
Host: localhost
Porta: 5433
Banco: organizador_financas
Usuário: postgres
```

A porta `5433` é utilizada no projeto porque existe uma instalação local do PostgreSQL utilizando a porta padrão `5432`.

O PostgreSQL do Docker utiliza o mapeamento:

```text
5433 → 5432
```

ou seja:

```text
localhost:5433 → PostgreSQL no container:5432
```

## Status

🚧 **Projeto em desenvolvimento**

### Implementado

* [x] Estrutura inicial do projeto
* [x] Configuração do backend com Spring Boot
* [x] Configuração do PostgreSQL
* [x] PostgreSQL em container Docker
* [x] Configuração de ambiente local
* [x] Integração Spring Boot + PostgreSQL
* [x] Entidade `Categoria`
* [x] Repository de `Categoria`
* [x] Service de `Categoria`
* [x] Controller de `Categoria`
* [x] CRUD de categorias
* [x] Tratamento de categorias inexistentes (`404`)
* [x] Testes manuais da API utilizando Postman

### Próximos passos

* [ ] Melhorar validações da API
* [ ] Implementar DTOs
* [ ] Implementar autenticação e autorização
* [ ] Criar entidades de receitas e despesas
* [ ] Relacionar movimentações com categorias
* [ ] Implementar regras de negócio financeiras
* [ ] Desenvolver frontend web
* [ ] Desenvolver aplicação mobile
