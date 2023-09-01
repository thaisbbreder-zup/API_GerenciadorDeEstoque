# Gerenciador de Estoque - 📦🚀
Este é um sistema de gerenciamento de estoque simples e eficaz, desenvolvido em Java, usando o framework Spring Boot.

### 🛠️ Dependências
* __Spring Boot:__ Um framework para criação de aplicativos Java de maneira rápida e fácil.
* __Spring Data JPA:__ Facilita o acesso a dados em bancos de dados relacionais.
* __JUnit 5:__ Framework de teste para testes unitários em Java.
* __Mockito:__ Biblioteca de mocking para testes.
* __Docker:__ Plataforma de virtualização de contêiner.
* __PostgreSQL:__ Sistema de gerenciamento de banco de dados relacional de código aberto para armazenamento de dados.
* __Postman:__ Ferramenta para testar e depurar APIs por meio do envio de solicitações HTTP e visualização de respostas.
* __Jacoco:__ Ferramenta de análise de cobertura de código Java para medir a eficácia dos testes _mvn clean test jacoco:report_.
* __Maven:__ Gerenciador de dependências e construção de projetos.
* __Swagger:__ Para documentação da API, acessível em _http://localhost:8080/swagger-ui.html#/produto-controller_.

### 📋 Funcionalidades
Este projeto oferece as seguintes funcionalidades:
* __Cadastro de Produtos:__ Permite o cadastro de produtos com informações como nome, descrição, quantidade em estoque e preço unitário.
* __Atualização de Produtos:__ Permite atualizar informações de produtos já cadastrados.
* __Exclusão de Produtos:__ Possibilita a exclusão de produtos com base em seus IDs.
* __Listagem de Produtos:__ Permite listar todos os produtos cadastrados.
* __Busca de Produtos por Nome:__ Permite buscar produtos por nome.
* __Busca de Produto por ID:__ Permite buscar um produto específico pelo seu ID.

### ✅ Testes
Foram realizados testes abrangentes para garantir o funcionamento correto das funcionalidades:

* __Cadastro bem-sucedido:__ Teste de cadastro de um produto com todos os campos preenchidos corretamente.
* __Cadastro com campos em branco:__ Teste de cadastro de um produto com campos obrigatórios em branco.
* __Cadastro com valores inválidos:__ Teste de cadastro de um produto com valores de estoque e preço inválidos.
* __Listar produtos quando houver produtos cadastrados:__ Teste de listagem de produtos quando há produtos cadastrados no banco de dados.
* __Listar produtos quando não houver produtos cadastrados:__ Teste de listagem de produtos quando não há produtos cadastrados no banco de dados.
* __Listar produtos por nome existente:__ Teste de listagem de produtos por nome quando o nome existe.
* __Listar produtos por nome inexistente:__ Teste de listagem de produtos por nome quando o nome não existe.
* __Listar produto por ID existente:__ Teste de listagem de produtos por ID quando o ID existe.
* __Listar produtos por ID inexistente:__ Teste de listagem de produtos por ID quando o ID não existe.
* __Atualizar produto com dados válidos e ID existente:__ Teste de atualização de um produto com dados válidos e ID existente.
* __Atualizar com campos em branco:__ Teste de atualização de um produto com campos obrigatórios em branco.
* __Atualizar com valores inválidos:__ Teste de atualização de um produto com valores de estoque e preço inválidos.
* __Atualizar produtos quando o ID é inexistente:__ Teste de atualização de produtos quando o ID é inexistente.
* __Excluir produto por ID existente:__ Teste de exclusão de um produto por ID existente.
* __Excluir produto por ID inexistente:__ Teste de exclusão de um produto por ID inexistente.

<img width="807" alt="Captura de tela 2023-09-01 111325" src="https://github.com/thaisbbreder-zup/API_GerenciadorDeEstoque/assets/133882082/fbca0353-a0e6-48b0-94ea-0a17d8944b52">

### Como Usar

Siga estas etapas para configurar e executar o sistema de gerenciamento de estoque:

1. **Clone o Repositório:**

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git

2. cd seu-repositorio
3. mvn spring-boot:run

