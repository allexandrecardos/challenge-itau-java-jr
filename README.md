
# 💼 Itaú Unibanco - Desafio de Programação

## 📄 Instruções do Desafio

Instruções do desafio: [Instruções do Desafio - GitHub](https://github.com/allexandrecardos/challenge-itau-java-jr/tree/main/challenge-instructions)

---

> **OBS:** Os nomes dos endpoints e campos foram adaptados para inglês para melhor padronização e clareza no desenvolvimento.

## 📌 Visão Geral
API REST para processamento de transações financeiras e cálculo de estatísticas em tempo real.  
Desenvolvida em **Java 21** com **Spring Boot 3.4.5**, armazena dados em memória e atende aos requisitos do desafio proposto.

---

## 🚀 Tecnologias Utilizadas

- ✅ **Java 21**  
- ✅ **Spring Boot 3.4.5**  
- ✅ **Spring Validation (3.4.4)**  
- ✅ **Lombok (1.18.38)**  
- ✅ **JUnit 5** – Testes automatizados (via `spring-boot-starter-test`)  
- ✅ **Docker** – Containerização  
- ✅ **Springdoc OpenAPI (2.8.6)** – Documentação Swagger UI  
- ✅ **Swagger Annotations (2.2.28)** – Anotações para Swagger/OpenAPI 

---

## ▶️ Como Rodar o Projeto

### Pré-requisitos
- JDK 21 instalado  
- Docker (opcional, para rodar via container)  

### Com Docker
```bash
docker compose up -d
```
Ou para rodar em ambiente de desenvolvimento ou produção:
```bash
docker compose up -d dev
docker compose up -d prod
```

### Sem Docker
```bash
./mvnw spring-boot:run
```
ou
```bash
mvn spring-boot:run
```

---

## ✅ Validações da Requisição

- O campo `value` deve ser **maior ou igual a 0**  
- O campo `datetime` **não pode ser uma data futura**  
- Os campos `value` e `datetime` são **obrigatórios**  
- O campo `datetime` deve seguir o formato **ISO 8601**  

---

## 📨 Endpoints

### 🔹 `POST /transaction`  
Registra uma nova transação financeira.

**Request:**
```json
{
  "value": 123.45,
  "datetime": "2024-01-01T14:30:00-03:00"
}
```

**Responses:**  

| Código | Status                | Descrição           |
|--------|-----------------------|---------------------|
| 201    | Created               | Transação aceita    |
| 422    | Unprocessable Entity  | Dados inválidos     |
| 400    | Bad Request           | Formato inválido    |

---

### 🔸 `DELETE /transaction`  
Remove todas as transações armazenadas.

**Responses:**  

| Código | Status  | Descrição                            |
|--------|---------|------------------------------------|
| 200    | OK      | Todas as transações foram removidas |

---

### 🔹 `GET /statistic`  
Retorna estatísticas das transações dos **últimos 60 segundos**.

**Responses:**  

| Código | Status | Descrição                                   |
|--------|--------|---------------------------------------------|
| 200    | OK     | Retorna estatísticas das últimas transações |

**Exemplo de Response:**
```json
{
  "count": 5,
  "sum": 150.75,
  "avg": 30.15,
  "min": 10.25,
  "max": 50.50
}
```

---

## ✨ Extras Implementados

- ✅ **Dockerização** para facilitar execução e deploy  
- ✅ **Testes automatizados** com JUnit 5 para garantir qualidade e funcionamento da API  
- ✅ **Integração contínua (CI)** configurada para executar testes automaticamente  
- ✅ **Documentação da API via Swagger** disponível para consulta e testes  
