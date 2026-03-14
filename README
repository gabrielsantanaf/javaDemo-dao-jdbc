# 📦 Demo DAO — Acesso a Banco de Dados com JDBC

Projeto de estudo desenvolvido para aprender os principais recursos do **JDBC (Java Database Connectivity)**, implementando o padrão **DAO (Data Access Object)** manualmente com Java puro e MySQL.

---

## 🎯 Objetivos

- Conhecer os principais recursos do JDBC na teoria e prática
- Elaborar a estrutura básica de um projeto com JDBC
- Implementar o padrão DAO manualmente com JDBC

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 21.0.7 |
| MySQL Server | 8.x |
| MySQL Connector/J | 8.x |
| IntelliJ IDEA | Community ou Ultimate |
| MySQL Workbench | Qualquer versão recente |

---

## 📋 Pré-requisitos

- [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)
- [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)
- [MySQL Connector/J (.jar)](https://dev.mysql.com/downloads/connector/j/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Community ou Ultimate)

---

## ⚙️ Configuração do Ambiente

### 1. Banco de dados

No **MySQL Workbench**, crie o banco de dados:

```sql
CREATE DATABASE coursejdbc;
```

Em seguida, execute o script de criação das tabelas (disponível na seção [Estrutura do Banco de Dados](#-estrutura-do-banco-de-dados)).

---

### 2. Adicionando o MySQL Connector/J no IntelliJ IDEA

Existem duas formas de adicionar a dependência no IntelliJ:

#### ✅ Opção A — Via arquivo `.jar` (sem Maven/Gradle)

1. [Baixe o MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) e extraia o `.jar`
2. Na barra superior do IntelliJ: **File → Project Structure** (ou `Ctrl + Alt + Shift + S`)
3. No menu lateral, clique em **Modules**
4. Vá para a aba **Dependencies**
5. Clique no **`+`** no canto inferior esquerdo → **JARs or Directories...**
6. Localize e selecione o arquivo `mysql-connector-j-x.x.x.jar`
7. Confirme que o escopo está como **Compile**
8. Clique em **Apply → OK**

#### ✅ Opção B — Via Maven (recomendado)

Se o projeto usa Maven, adicione a dependência no `pom.xml`:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
```

Após colar, clique no ícone de **elefante (🐘)** que aparece no canto superior direito para sincronizar, ou use **Maven → Reload Project** no painel lateral.

---

### 3. Criação do Projeto

1. **File → New → Project**
2. Selecione **Java**, defina o **JDK 21** e clique em Next
3. Dê um nome ao projeto e finalize
4. Adicione o MySQL Connector conforme a **Opção A ou B** acima

---

### 4. Arquivo de Configuração (`db.properties`)

Na **pasta raiz do projeto**, crie um arquivo chamado `db.properties` com o seguinte conteúdo:

```properties
user=developer
password=1234567
dburl=jdbc:mysql://localhost:3306/coursejdbc
useSSL=false
```

> ⚠️ Ajuste `user` e `password` de acordo com as credenciais do seu MySQL.

---

## 🗂️ Estrutura do Projeto

```
demo-dao-jdbc/
│
├── src/
│   ├── application/          # Classes main para testes
│   ├── db/
│   │   ├── DB.java           # Classe utilitária de conexão com o banco
│   │   └── DbException.java  # Exceção personalizada
│   ├── model/
│   │   ├── dao/
│   │   │   ├── DaoFactory.java
│   │   │   ├── SellerDao.java       # Interface DAO
│   │   │   └── DepartmentDao.java   # Interface DAO
│   │   ├── dao/impl/
│   │   │   ├── SellerDaoJDBC.java       # Implementação com JDBC
│   │   │   └── DepartmentDaoJDBC.java   # Implementação com JDBC
│   │   └── entities/
│   │       ├── Seller.java
│   │       └── Department.java
│
└── db.properties             # Configurações de conexão (não versionar!)
```

---

## 🗃️ Estrutura do Banco de Dados

> Cole abaixo o script SQL de criação das tabelas quando disponível.

```sql
CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE seller (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

INSERT INTO department (Name) VALUES
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
```

---

## 🔌 Classe `DB.java` — Conexão com o Banco

A classe `DB` centraliza a abertura e fechamento de conexões, utilizando o arquivo `db.properties`:

```java
// Abrindo conexão
Connection conn = DB.getConnection();

// Fechando recursos
DB.closeConnection();
DB.closeStatement(st);
DB.closeResultSet(rs);
```

---

## 🏗️ Padrão DAO Implementado

O padrão **DAO** separa a lógica de acesso a dados do restante da aplicação:

```
Interface (SellerDao)
    └── Implementação (SellerDaoJDBC)
            └── Usa DB.java para obter Connection
                    └── Executa SQL via PreparedStatement
```

Exemplo de operações implementadas:

| Operação | Método |
|---|---|
| Inserir | `insert(Seller obj)` |
| Atualizar | `update(Seller obj)` |
| Deletar por ID | `deleteById(Integer id)` |
| Buscar por ID | `findById(Integer id)` |
| Buscar todos | `findAll()` |
| Buscar por departamento | `findByDepartment(Department dep)` |

---

## 📚 Referências

- [JDBC Guide — Oracle Docs](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
- [java.sql Package — Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)
- [Repositório original do curso](https://github.com/acenelio/jdbc1)

---

## 🙋 Autor

Desenvolvido como projeto de estudo seguindo o curso de Java do professor **Nelio Alves**.