# Popcorn Prose Server

O **Popcorn Prose Server** é uma API RESTful desenvolvida em Spring Boot para fornecer recursos relacionados a avaliações de filmes e séries disponíveis na API The Movie Database (TMDB).

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
    mvnw.cmd spring-boot:run
    ```

    Ou executando o arquivo `PopcornProseServerApplication` em uma IDE.

## Uso
A API estará acessível em [http://localhost:8080](http://localhost:8080).

### Endpoints - Usuário
-  `POST auth/cadastrar` - Cadastra um novo usuário.
-  `POST auth/login` - Realiza o login de um usuário
-  `PUT auth/adicionar-foto/{userID}` - Edita a foto de um usuário.

### Endpoints - Reviews
- `POST review/addReview` - Realiza o cadastro de uma nova review.
- `GET review/getReview` - Resgata a review de um filme com base no ID do usuário.
- `GET review/allReviews` - Resgata todas as reviews do usuário.
- `PUT review/editReview` - Realiza a edição de uma review.
- `DELETE review/deleteReview` - Deleta uma review.

### Endpoints - Lista de desejos
- `POST lista-desejos/adicionar` - Cadastra um filme em uma lista.
- `GET lista-desejos/listar/{userId}` - Resgata todos os filmes da lista do usuário.
- `DELETE lista-desejos/delete/{filmeId}/{userId}` - Deleta um filme da lista do usuário


## Tecnologias Utilizadas
- Spring Boot
- Spring Data JPA
- PostgreSQL
- JSON Web Token (JWT)
