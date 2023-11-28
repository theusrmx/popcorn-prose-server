# Popcorn Prose Server

O **Popcorn Prose Server** é uma API RESTful desenvolvida em Spring Boot para fornecer recursos relacionados a avaliações de filmes e séries disponivéis na API The Movie Database (TMDB).

## Pré-requisitos
- Java (versão 17 ou superior)
- PostgreSQL

## Configuração
1. Clone este repositório:

    ```bash
    git clone https://seurepositorio.com/popcorn-prose-server.git
    ```

2. Configurações do banco de dados:
   - Crie um banco de dados PostgreSQL chamado *popcorn_prose*.
   - Atualize as informações de conexão do banco de dados no arquivo `src/main/resources/application.properties`.

3. Execute o projeto:

    ```bash
    ./mvnw spring-boot:run
    ```

    ou, se você estiver no Windows:

    ```bash
    mvnw.cmd spring-boot:run
    ```

## Uso
A API estará acessível em [http://localhost:8080](http://localhost:8080). Consulte a documentação da API para obter informações sobre os endpoints disponíveis.

## Tecnologias Utilizadas
- Spring Boot
- Spring Data JPA
- PostgreSQL
- JSON Web Token (JWT)
