# Sistema de Gerenciamento de Pedidos

Este é um sistema de gerenciamento de pedidos desenvolvido com Spring Boot, JPA/Hibernate e PostgreSQL.

## Funcionalidades

- Gerenciamento de usuários (CRUD)
- Gerenciamento de pedidos (CRUD)
- Gerenciamento de produtos (CRUD)
- Gerenciamento de categorias (CRUD)
- Associação de produtos a categorias
- Cálculo de total de pedidos
- Tratamento de exceções personalizado

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0.0
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JUnit 5
- Mockito

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/educandoweb/course/
│   │       ├── config/         # Configurações do projeto
│   │       ├── entities/       # Entidades JPA
│   │       ├── repositories/   # Repositórios JPA
│   │       ├── resources/      # Controladores REST
│   │       └── services/       # Lógica de negócio
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/educandoweb/course/
            └── services/       # Testes unitários
```

## Configuração do Ambiente

1. Clone o repositório
2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `springboot_course`
   - Configure as credenciais no arquivo `application.properties`
3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints da API

### Usuários
- `GET /users` - Lista todos os usuários
- `GET /users/{id}` - Busca usuário por ID
- `POST /users` - Cria novo usuário
- `PUT /users/{id}` - Atualiza usuário
- `DELETE /users/{id}` - Remove usuário

### Pedidos
- `GET /orders` - Lista todos os pedidos
- `GET /orders/{id}` - Busca pedido por ID

### Produtos
- `GET /products` - Lista todos os produtos
- `GET /products/{id}` - Busca produto por ID

### Categorias
- `GET /categories` - Lista todas as categorias
- `GET /categories/{id}` - Busca categoria por ID

## Tratamento de Exceções

O sistema possui tratamento personalizado para as seguintes exceções:
- `ResourceNotFoundException` - Quando um recurso não é encontrado
- `DatabaseException` - Quando ocorre erro de integridade no banco de dados

## Testes

O projeto inclui testes unitários para todos os serviços:
- UserServiceTest
- OrderServiceTest
- ProductServiceTest
- CategoryServiceTest

Para executar os testes:
```bash
mvn test
```

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
