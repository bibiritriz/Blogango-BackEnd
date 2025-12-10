## 💡 Visão Geral e Propósito

O `Blogango-BackEnd` é o componente de *backend* de um projeto de blog simplificado, desenvolvido como parte da disciplina de Programação em Banco de Dados. Ele fornece uma **API RESTful** para gerenciar dados de posts, comentários e categorias, desacoplando a lógica de negócios da interface do usuário.

### Arquitetura

O projeto segue uma arquitetura **orientada a camadas** padrão em aplicações Spring Boot.

*   **Controladores (`controller`):** Recebem requisições HTTP, delegam a lógica para a camada de serviço e retornam as respostas.
*   **Serviços (`service`):** Contêm a lógica de negócios da aplicação.
*   **Repositórios (`repository`):** Interagem diretamente com o banco de dados MongoDB.
*   **Modelos (`model`):** Representam a estrutura dos dados no MongoDB.

### Banco de Dados

O projeto utiliza o **MongoDB** como banco de dados NoSQL, ideal para a flexibilidade e escalabilidade necessárias em aplicações de blog. A conexão é gerenciada pelo Spring Data MongoDB.

---

## 🛠️ Tecnologias Envolvidas

As principais tecnologias e dependências utilizadas neste projeto são:

| Categoria | Tecnologia | Versão/Detalhe |
| :--- | :--- | :--- |
| **Linguagem** | Java | 17 |
| **Framework** | Spring Boot | 4.0.0 (Parent Version) |
| **Build Tool** | Maven | (Gerenciado pelo `pom.xml`) |
| **Banco de Dados** | MongoDB | Spring Data MongoDB |
| **Web** | Spring Web MVC | Para criação de APIs RESTful |
| **Validação** | Spring Boot Starter Validation | Para validação de dados de entrada |
| **Produtividade** | Lombok | Para reduzir código boilerplate |
| **Mapeamento** | MapStruct | 1.6.3 (Para mapeamento de DTOs) |

---

## ⚙️ Pré-requisitos e Instalação

Para executar este projeto localmente, você precisará ter instalado:

1.  **Java Development Kit (JDK):** Versão 17 ou superior.
2.  **Apache Maven:** Para gerenciar as dependências e o build do projeto.
3.  **MongoDB:** Uma instância local ou remota do MongoDB em execução.

### Configuração do Ambiente

O projeto requer a configuração de uma variável de ambiente para a conexão com o banco de dados.

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/bibiritriz/Blogango-BackEnd
    cd Blogango-BackEnd
    ```

2.  **Configure a URI do MongoDB:**

    Crie a variável de ambiente `MONGODB_URI` com a string de conexão do seu banco de dados.

    *Exemplo (Linux/macOS):*
    ```bash
    export MONGODB_URI="mongodb://localhost:27017/blogango_db"
    ```

    *Exemplo (Windows - PowerShell):*
    ```powershell
    $env:MONGODB_URI="mongodb://localhost:27017/blogango_db"
    ```

3.  **Instale as dependências:**

    Utilize o Maven Wrapper para garantir a versão correta do Maven.

    ```bash
    ./mvnw clean install
    ```

---

## ▶️ Como Executar

### Execução em Modo de Desenvolvimento

Após a instalação das dependências, você pode iniciar a aplicação Spring Boot:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada e estará acessível em `http://localhost:8080`.

### Execução de Testes

Para executar os testes unitários e de integração definidos no projeto:

```bash
./mvnw test
```

---

## 🔗 Uso da API (Endpoints)

O `Blogango-BackEnd` expõe endpoints RESTful para o gerenciamento dos recursos do blog. Embora a documentação completa dos endpoints (como Swagger/OpenAPI) não esteja configurada, a estrutura do projeto sugere os seguintes recursos principais:

| Recurso | Método HTTP | Endpoint Sugerido | Descrição |
| :--- | :--- | :--- | :--- |
| **Posts** | `POST` | `/api/posts` | Cria um novo post no blog. |
| | `GET` | `/api/posts` | Lista todos os posts. |
| | `GET` | `/api/posts/{id}` | Recupera um post específico por ID. |
| | `PUT` | `/api/posts/{id}` | Atualiza um post existente. |
| | `DELETE` | `/api/posts/{id}` | Remove um post. |
| **Comentários** | `POST` | `/api/comentarios` | Cria um novo comentário. |
| | `GET` | `/api/comentarios/{postId}` | Recupera todos os comentários de um post. |
| | `PUT` | `/api/comentarios/{id}` | Edita um comentário existente. |
| | `DELETE` | `/api/comentarios/{id}` | Deleta um comentário. |
| **Categorias** | `GET` | `/api/categorias` | Lista todas as categorias. |
| | `POST` | `/api/categorias` | Cria uma nova categoria. |

---

## 📧 Créditos e Contato

Este projeto foi criado por **bibiritriz**.

Para dúvidas, sugestões ou contato, você pode acessar o perfil do autor no GitHub:

[GitHub de bibiritriz](https://github.com/bibiritriz)
