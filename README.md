# microsservicos
Trabalho de microserviços da pós Engenharia de Software - Infnet

## Banco de Dados

Para testes locais foi utilizado o banco de dados em abiente docker com o docker-compose.yml

~~~
version: "2"

services:
  db:
    image: mysql
    container_name: mysql-microsservicos
    volumes:
      - ./data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: microsservicos
    ports:
      - 5001:3306
~~~



## Documentação

- Para testes e acesso a documentação da API Clientes acesse a url http://localhost:8282/swagger-ui.html

- Para testes e acesso a documentação da API Catálogo acesse a url http://localhost:8383/swagger-ui.html

- Para testes e acesso a documentação da API Locação acesse a url http://localhost:8181/swagger-ui.html


