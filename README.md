
# Projeto de Gerenciamento de Tarefas

Este projeto é uma aplicação de gerenciamento de tarefas desenvolvida em **Java** utilizando **JSF** (JavaServer Faces) com integração com o **JPA** (Java Persistence API) para persistência de dados. A aplicação permite que os usuários possam cadastrar, listar, atualizar e remover tarefas, bem como realizar a conclusão das mesmas. Há também filtros dinâmicos que permitem listar as tarefas de acordo com diferentes critérios como número da tarefa, título ou descrição, responsável e situação (Em andamento ou Concluída).

## Funcionalidades Implementadas

- **Cadastro de Tarefas**: Tela para cadastrar uma nova tarefa, associando título, descrição, responsável, prioridade e data de deadline.
- **Listagem de Tarefas com Filtros**: Exibe uma lista de tarefas com possibilidade de aplicar filtros de número, título, descrição, responsável e situação.
- **Atualização de Tarefas**: Possibilidade de editar os dados de uma tarefa existente.
- **Remoção de Tarefas**: Exclusão de uma tarefa da lista.
- **Concluir Tarefas**: Marcação de tarefas como concluídas, removendo-as da listagem de tarefas em andamento.
- **Filtros Dinâmicos**: Permite filtrar a listagem de tarefas de acordo com diferentes parâmetros fornecidos.

## Estrutura do Projeto

- **Entidades**: As classes de entidades representam os objetos que serão persistidos no banco de dados (ex.: `TarefaEntity`, `ResponsavelEntity`).
- **Repositórios**: Os repositórios (`TarefaRepository`, `ResponsavelRepository`) são responsáveis por executar operações de banco de dados como inserções, atualizações, remoções e consultas.
- **Serviços**: A camada de serviço centraliza a lógica de negócios da aplicação.
- **Controllers**: Os controladores JSF são responsáveis por intermediar a interação do usuário com a aplicação, servindo como interface entre a camada de visão (JSF) e o back-end.

### Funcionalidades do Repositório `TarefaRepository`
- `findByFilters`: Método que retorna a lista de tarefas filtrada de acordo com parâmetros opcionais como número, título/descrição, responsável e situação.
- `deleteById`: Método para excluir uma tarefa pelo seu ID.
- `save`: Método para salvar ou atualizar uma tarefa.
- `findById`: Método para buscar uma tarefa específica por ID.

## Requisitos do Sistema

Antes de executar o projeto, certifique-se de que seu ambiente de desenvolvimento atende aos seguintes requisitos:

- **Java**: JDK 8 ou superior.
- **Apache Maven**: Para gerenciar dependências e compilar o projeto.
- **Apache Tomcat**: Servidor de aplicação para rodar a aplicação JSF.
- **Banco de Dados**: O projeto foi configurado para uso com o **PostgreSQL**, mas pode ser adaptado para outros SGBDs compatíveis com JPA/Hibernate.
- **IDE**: IntelliJ IDEA, Eclipse ou qualquer IDE compatível com projetos Maven e Java EE.

## Configuração do Banco de Dados (PostgreSQL)

Antes de executar a aplicação, você precisará configurar o banco de dados PostgreSQL. Aqui estão as instruções básicas:

1. Crie um banco de dados PostgreSQL:
   ```sql
   CREATE DATABASE tarefasdb;
   ```

   ```sql
   CREATE USER username WITH PASSWORD 'password';   
   GRANT ALL PRIVILEGES ON DATABASE tarefasdb TO username;


    
    CREATE TABLE responsaveis (
        id SERIAL PRIMARY KEY,
        name VARCHAR(150)
    );

    CREATE TABLE tarefas (
        numero SERIAL PRIMARY KEY,
        titulo VARCHAR(150) NOT NULL,
        responsavel INTEGER, 
        prioridade VARCHAR(10) NOT NULL,
        situacao VARCHAR(100) NOT NULL DEFAULT 'Em Andamento',
        deadline DATE,
        CONSTRAINT fk_responsavel FOREIGN KEY (responsavel) REFERENCES responsaveis(id) 
    );

   ```

2. Atualize o arquivo `persistence.xml` para apontar para o banco de dados criado:
   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/tarefasdb" />
   <property name="javax.persistence.jdbc.user" value="seu_usuario" />
   <property name="javax.persistence.jdbc.password" value="sua_senha" />
   ```

3. O projeto utiliza **JPA** para mapear as entidades no banco de dados automaticamente.

## Executando o Projeto Localmente



1. Importe o projeto em sua IDE (como IntelliJ ou Eclipse) como um projeto Maven existente.

2. Compile o projeto usando Maven:
   ```bash
   mvn clean install
   ```

3. Configure o servidor **Apache Tomcat** na sua IDE e adicione o projeto à configuração do Tomcat.

4. Inicie o servidor Tomcat a partir da sua IDE.

5. Acesse a aplicação no navegador:
   ```
   http://localhost:8080/gerenciamento-tarefas/
   ```

## Testando a Aplicação

- **Cadastrar uma tarefa**: Vá para a página de cadastro de tarefas, preencha o formulário com título, descrição, responsável, prioridade e deadline e clique em "Cadastrar".
- **Listar tarefas**: Acesse a página de listagem de tarefas e use os filtros para buscar tarefas por número, título/descrição, responsável ou situação.
- **Editar uma tarefa**: Na listagem de tarefas, clique em "Editar" ao lado da tarefa desejada, faça as alterações e salve.
- **Excluir uma tarefa**: Na listagem de tarefas, clique em "Excluir" ao lado da tarefa desejada.
- **Concluir uma tarefa**: Na listagem de tarefas, clique em "Concluir" para marcar a tarefa como concluída.

---
