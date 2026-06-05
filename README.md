# Task Management API

REST API para gerenciamento de tarefas desenvolvida com Spring Boot.

O projeto implementa autenticação JWT, persistência com MySQL, versionamento de banco utilizando Flyway e conteinerização com Docker.

---

## Implementações

- Cadastro de usuários
- Login com JWT
- Atualização de usuários
- Exclusão de usuários
- Criação de tarefas
- Atualização de tarefas
- Exclusão de tarefas
- Listagem paginada de tarefas
- Validação de dados
- Migrações automáticas com Flyway
- Documentação Swagger/OpenAPI
- Conteinerização com Docker

---

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Flyway
- JWT (Auth0 Java JWT)
- Swagger / OpenAPI
- Docker & Docker Compose
- Maven
- MapStruct
- Lombok

---

## Fluxo e Arquitetura de pastas

```text
Client
  ↓
Spring Security
  ↓
JWT Filter
  ↓
Controllers
  ↓
Services
  ↓
Repositories
  ↓
MySQL
```

```text
src
├── auth
│   ├── config
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── util
│
├── task
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── util
│
└── infra
    ├── security
    ├── exception
    └── config
```

---

## Fluxo de autenticação

```text
Registrar
↓
Login
↓
JWT Token
↓
Autorizar
↓
Endpoints protegidos
```

---

## Como executar o código? (Utilizando docker)

Clone o repositório:

```bash
git clone https://github.com/viniciusvieia12/task-management-api.git
```

Acesse o projeto anteriormente clonado:

```bash
cd task-management-api
```

Inicie os conteiners da maneira adequada:

```bash
docker compose --env-file .env.docker up
```

API:

```text
http://localhost:8080
```

---

## Execução local

Caso deseje executar sem utilizar o docker

Requisitos:

- Java 21
- Maven
- MySQL

Comando para build:

```bash
mvn clean package
```

Comando para rodar o código:

```bash
mvn spring-boot:run
```

---

# Variáveis de Ambiente

```markdown
| Variable       | Description         |
|----------------|---------------------|
| DB_KEY         | Database URL        |
| DB_USERNAME    | Database username   |
| DB_PASSWORD    | Database password   |
| JWT_SECRET     | JWT secret key      |
| JWT_EXPIRATION | Token expiration    |
```

---

## Documentação de API

A documentação da API está disponível através do Swagger/OpenAPI.

Swagger: http://localhost:8080/swagger-ui/index.html

### Autenticação JWT

Dentro do Swagger os endpoints protegidos também exigem um token JWT válido.

- Siga o seguinte fluxo para autenticação dentro do Swagger:

1. Registrar um usuário
2. Realizar login e obter um token JWT
3. Clique no botão **Authorize**
4. Cole o token obtido anteiormente
5. Agora, acesse os endpoints protegidos

---

## Implementações futuras

- Controle de acesso baseado em roles (ADMIN / USER)
- Testes de integração
- Testes de unidade

---

## Author

Vinicius Vieira

LinkedIn: https://www.linkedin.com/in/vinicius-freitas-vieira