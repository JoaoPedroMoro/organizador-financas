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
│                   ├── dto/
│                   │   ├── request/
│                   │   └── response/
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

Além disso, a API utiliza DTOs para separar os dados recebidos e enviados pelas requisições das entidades utilizadas internamente pelo JPA.

## Categorias

O gerenciamento de categorias permite organizar as movimentações financeiras de acordo com sua finalidade.

A entidade `Categoria` possui atualmente:

* `id`
* `nome`

O CRUD de categorias está implementado:

| Método | Endpoint | Descrição |
| ------ | -------- | --------- |
| GET | `/categorias` | Lista todas as categorias |
| GET | `/categorias/{id}` | Busca uma categoria pelo ID |
| POST | `/categorias` | Cria uma nova categoria |
| PUT | `/categorias/{id}` | Atualiza uma categoria |
| DELETE | `/categorias/{id}` | Remove uma categoria |

### Criando uma categoria

Para criar uma categoria, envie uma requisição `POST` para:

```text
POST /categorias
```

Com o seguinte JSON:

```json
{
    "nome": "Alimentação"
}
```

Uma resposta de sucesso possui o seguinte formato:

```json
{
    "nome": "Alimentação",
    "id": 1
}
```

### Buscando categorias

Para listar todas as categorias:

```text
GET /categorias
```

Para buscar uma categoria específica:

```text
GET /categorias/1
```

Exemplo de resposta:

```json
{
    "nome": "Alimentação",
    "id": 1
}
```

### Atualizando uma categoria

Para atualizar uma categoria existente:

```text
PUT /categorias/1
```

JSON:

```json
{
    "nome": "Alimentação e Mercado"
}
```

### Excluindo uma categoria

Para excluir uma categoria:

```text
DELETE /categorias/1
```

Categorias que possuem movimentações associadas não devem ser removidas enquanto essas movimentações estiverem vinculadas à categoria.

## Movimentações

O recurso de movimentações permite registrar receitas e despesas e associá-las a uma categoria.

A entidade `Movimentacao` possui atualmente:

* `id`
* `descricao`
* `valor`
* `data`
* `tipo`
* `categoria`

Uma movimentação possui um relacionamento `ManyToOne` com a entidade `Categoria`.

```text
Categoria
    │
    └── 1:N ── Movimentacao
```

Uma categoria pode estar associada a várias movimentações, enquanto cada movimentação pertence a uma categoria.

O CRUD de movimentações está implementado:

| Método | Endpoint | Descrição |
| ------ | -------- | --------- |
| GET | `/movimentacoes` | Lista todas as movimentações |
| GET | `/movimentacoes/{id}` | Busca uma movimentação pelo ID |
| POST | `/movimentacoes` | Cria uma nova movimentação |
| PUT | `/movimentacoes/{id}` | Atualiza uma movimentação |
| DELETE | `/movimentacoes/{id}` | Remove uma movimentação |

### Criando uma movimentação

Para criar uma movimentação, primeiro é necessário possuir uma categoria cadastrada.

Considerando que a categoria `Alimentação` possui o ID `1`, envie:

```text
POST /movimentacoes
```

JSON:

```json
{
    "descricao": "Almoço",
    "valor": 35.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

A API valida se a categoria informada existe antes de salvar a movimentação.

Uma resposta de sucesso possui o seguinte formato:

```json
{
    "id": 1,
    "descricao": "Almoço",
    "valor": 35.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoria": {
        "id": 1,
        "nome": "Alimentação"
    }
}
```

### Tipos de movimentação

As movimentações podem representar receitas e despesas.

Atualmente, os tipos utilizados pela API são:

```text
RECEITA
DESPESA
```

Exemplo de uma receita:

```json
{
    "descricao": "Salário",
    "valor": 3500.00,
    "data": "2026-08-17",
    "tipo": "RECEITA",
    "categoriaId": 2
}
```

Exemplo de uma despesa:

```json
{
    "descricao": "Supermercado",
    "valor": 250.75,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

### Buscando movimentações

Para listar todas as movimentações:

```text
GET /movimentacoes
```

Exemplo de resposta:

```json
[
    {
        "id": 1,
        "descricao": "Almoço",
        "valor": 35.90,
        "data": "2026-08-17",
        "tipo": "DESPESA",
        "categoria": {
            "id": 1,
            "nome": "Alimentação"
        }
    }
]
```

Para buscar uma movimentação específica:

```text
GET /movimentacoes/1
```

### Atualizando uma movimentação

Para atualizar uma movimentação existente:

```text
PUT /movimentacoes/1
```

JSON:

```json
{
    "descricao": "Almoço com amigos",
    "valor": 42.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

### Excluindo uma movimentação

Para excluir uma movimentação:

```text
DELETE /movimentacoes/1
```

Em caso de sucesso, a API retorna:

```text
204 No Content
```

## DTOs

A API utiliza DTOs para controlar os dados recebidos e enviados pelos endpoints.

Os DTOs estão organizados em:

```text
dto/
├── request/
└── response/
```

Os objetos de `request` representam os dados recebidos pela API.

Os objetos de `response` representam os dados devolvidos pela API.

Essa separação evita que as entidades JPA sejam utilizadas diretamente como contrato da API e facilita futuras alterações na estrutura interna da aplicação.

## Validações

A API utiliza validações para garantir que os dados obrigatórios sejam informados corretamente.

Entre as validações implementadas estão:

* Descrição obrigatória
* Valor obrigatório
* Valor maior que zero
* Data obrigatória
* Tipo da movimentação obrigatório
* Nome da categoria obrigatório

Por exemplo, uma movimentação com valor inválido:

```json
{
    "descricao": "Almoço",
    "valor": 0,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

é rejeitada pela API.

As validações são realizadas antes da execução das regras de negócio.

## Tratamento de erros

A API possui tratamento global de exceções através do `GlobalExceptionHandler`.

Entre os erros tratados estão:

* Recursos não encontrados
* Dados inválidos enviados na requisição
* Categorias inexistentes
* Regras relacionadas à exclusão de categorias
* Erros de validação dos campos

### Categoria inexistente

Ao solicitar uma categoria que não existe:

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

### Categoria inexistente ao criar movimentação

Caso uma movimentação seja criada utilizando uma categoria que não existe:

```json
{
    "descricao": "Almoço",
    "valor": 35.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 999
}
```

a API impede a criação da movimentação e retorna um erro informando que a categoria não foi encontrada.

### Estrutura de exceções

O tratamento de exceções está organizado no pacote:

```text
exception/
├── CategoriaNotFoundException.java
├── ErrorResponse.java
└── GlobalExceptionHandler.java
```

O `ErrorResponse` padroniza as respostas de erro da API:

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

Atualmente, o banco possui as seguintes tabelas:

```text
public
├── categoria
└── movimentacao
```

A tabela `movimentacao` possui uma referência para a tabela `categoria`, representando o relacionamento entre os dois recursos.

## Testando a API

Os endpoints podem ser testados utilizando ferramentas como **Postman**.

Uma sequência simples para testar a aplicação é:

### 1. Criar uma categoria

```text
POST /categorias
```

```json
{
    "nome": "Alimentação"
}
```

### 2. Criar outra categoria

```text
POST /categorias
```

```json
{
    "nome": "Transporte"
}
```

### 3. Criar uma movimentação

Considerando que `Alimentação` possui ID `1`:

```text
POST /movimentacoes
```

```json
{
    "descricao": "Almoço",
    "valor": 35.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

### 4. Listar movimentações

```text
GET /movimentacoes
```

### 5. Buscar uma movimentação

```text
GET /movimentacoes/1
```

### 6. Atualizar uma movimentação

```text
PUT /movimentacoes/1
```

```json
{
    "descricao": "Almoço com amigos",
    "valor": 45.90,
    "data": "2026-08-17",
    "tipo": "DESPESA",
    "categoriaId": 1
}
```

### 7. Excluir uma movimentação

```text
DELETE /movimentacoes/1
```

### 8. Excluir uma categoria

Uma categoria sem movimentações associadas pode ser removida:

```text
DELETE /categorias/2
```

Caso existam movimentações associadas à categoria, a aplicação deve impedir a exclusão para preservar a integridade dos dados.

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
* [x] Entidade `Movimentacao`
* [x] Repository de `Movimentacao`
* [x] Service de `Movimentacao`
* [x] Controller de `Movimentacao`
* [x] CRUD de movimentações
* [x] Relacionamento entre `Movimentacao` e `Categoria`
* [x] DTOs de request
* [x] DTOs de response
* [x] Validações dos dados recebidos pela API
* [x] Tratamento global de exceções
* [x] Tratamento de categorias inexistentes (`404`)
* [x] Validação de categorias utilizadas por movimentações
* [x] Testes manuais da API utilizando Postman
* [x] Aprimorar as regras de negócio financeiras
* [x] Melhorar o controle dos tipos de movimentação

### Próximos passos

* [ ] Implementar filtros de movimentações
* [ ] Implementar consultas por período
* [ ] Implementar resumo financeiro
* [ ] Implementar autenticação e autorização
* [ ] Desenvolver frontend web
* [ ] Desenvolver aplicação mobile
* [ ] Criar testes automatizados