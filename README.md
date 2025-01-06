# Projeto Spring Angular - Pizza Store

Este é um projeto de demonstração de uma loja de pizzas utilizando **Spring Boot**, **Angular**, **Java Gradle**, **Swagger** e o banco de dados **H2**. O objetivo é fornecer uma plataforma simples para gerenciar clientes, pizzas e pedidos.

## Tecnologias Utilizadas

- **Backend**: 
  - **Spring Boot**: Framework para desenvolvimento da API RESTful.
  - **Swagger**: Usado para documentação e testes da API.
  - **H2 Database**: Banco de dados embutido para persistência de dados.
  - **Gradle**: Gerenciador de dependências e construção do projeto.

- **Frontend**:
  - **Angular**: Framework utilizado para construção da interface web.

## Funcionalidades

- **Customer CRUD**: Na página de **Customers**, é possível realizar as operações de criação, leitura, atualização e exclusão de clientes. 
- **Pizza e Order Pages**: As páginas de **Pizzas** e **Pedidos** estão disponíveis, mas não possuem funcionalidades de CRUD implementadas no momento.

## Como Rodar o Projeto

### Backend (Spring Boot)

1. Clone este repositório:
    ```bash
    git clone https://github.com/Coruja-me/pizza-store.git
    cd pizza-store/backend
    ```

2. Compile o projeto com Gradle:
    ```bash
    ./gradlew build
    ```

3. Rode o servidor:
    ```bash
    ./gradlew bootRun
    ```

4. O backend estará disponível em `http://localhost:8080`.

### Frontend (Angular)

1. Navegue até o diretório do frontend:
    ```bash
    cd pizza-store/frontend
    ```

2. Instale as dependências:
    ```bash
    npm install
    ```

3. Rode o servidor de desenvolvimento:
    ```bash
    ng serve
    ```

4. O frontend estará disponível em `http://localhost:4200`.

## Swagger

A documentação da API pode ser acessada em `http://localhost:8080/swagger-ui/` após iniciar o backend.

## Estrutura do Projeto

- **backend**: Contém o código do servidor Spring Boot com a configuração do Swagger e o banco H2.
- **frontend**: Contém a aplicação Angular, incluindo páginas para **Customers**, **Pizzas** e **Pedidos**.

## Observações

- **CRUD de Customers**: Funcionalidades completas de CRUD para **Clientes** estão disponíveis.
- **Pizzas e Pedidos**: As páginas de **Pizzas** e **Pedidos** estão visíveis no frontend, mas não possuem funcionalidades de CRUD implementadas ainda.

---
