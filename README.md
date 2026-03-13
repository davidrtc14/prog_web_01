# 📋 Exercício — Spring Boot + Thymeleaf: Lista de Pessoas com IMC

> **Disciplina:** Programação Avançada para Web  
> **Objetivo:** Criar uma aplicação Spring MVC com Thymeleaf que exiba uma lista de pessoas em uma tabela HTML com nome, CPF, peso, altura e IMC calculado, acessível pela rota `/pessoas`.

---

## 🗂️ Estrutura do Projeto

```
pessoas-imc/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/exemple/demo/
│       │       ├── DemoApplication.java
│       │       ├── model/
│       │       │   └── Pessoa.java
│       │       └── controller/
│       │           └── PessoaController.java
│       └── resources/
│           ├── templates/
│           │   └── pessoas.html
│           └── application.properties
└── pom.xml
```

---

## ⚙️ Pré-requisitos e Instalação de Dependências

### 1. Java Development Kit (JDK 17 ou superior)

O Spring Boot 3.x requer o **JDK 17+**.

**Windows:**
1. Acesse [https://adoptium.net](https://adoptium.net) e baixe o instalador do **Temurin JDK 17**
2. Execute o instalador e siga os passos
3. Verifique a instalação no terminal:
```bash
java -version
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
```

**macOS (via Homebrew):**
```bash
brew install openjdk@17
java -version
```

---

### 2. Maven

O Maven gerencia as dependências do projeto.

**Windows:** Baixe em [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) e adicione ao PATH.

**Linux:**
```bash
sudo apt install maven
```

**macOS:**
```bash
brew install maven
```

Verifique:
```bash
mvn -version
```

> 💡 **Dica:** Se utilizar o **IntelliJ IDEA** ou **VS Code com extensão Spring**, o Maven já vem integrado e os passos acima podem ser pulados.

---

### 3. IDE Recomendada

- [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) (gratuita)
- [VS Code](https://code.visualstudio.com/) com as extensões:
  - Extension Pack for Java
  - Spring Boot Extension Pack

---

## 🚀 Acessando o Projeto

### Clone e acesse o repositório

```bash
git clone https://github.com/davidrtc14/prog_web_01.git
cd prog_web_01
```

---

## 📝 Código do Projeto

### `pom.xml` — Dependências

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
```

---

### `application.properties` — Configuração da Porta

```properties
server.port=8080
```

---

### `Pessoa.java` — Model

```java
package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pessoa {
  private String nome;
  private String cpf;
  private float peso;
  private float altura;
  private float imc;

  public Pessoa(String nome, String cpf, float peso, float altura) {
    this.nome = nome;
    this.cpf = cpf;
    this.peso = peso;
    this.altura = altura;
    calcularIMC();
  }

  public void calcularIMC(){
    this.imc = (float) (this.peso / (Math.pow(this.altura, 2)));
  }
}

```

> **Fórmula do IMC:** `IMC = peso / (altura²)`

---

### `PessoaController.java` — Controller

```java
package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import com.example.demo.utils.ListaPessoas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PessoaController {

  ListaPessoas listaPessoas = new ListaPessoas();

  @GetMapping("/pessoas")
  public String pessoas(Model model){

    if(listaPessoas.getPessoas().isEmpty()){
      listaPessoas.add(new Pessoa("Fulano de Tal", "432.321.987-66", 67.5F, 1.75F));
      listaPessoas.add(new Pessoa("Cilano de Tal", "765.115.935-12", 80.2F, 1.93F));
    }

    model.addAttribute("pessoas", listaPessoas.getPessoas());

    return "pessoas";
  }
}

```

---

### `pessoas.html` — Template Thymeleaf

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<h1>
  Lista de Pessoas
</h1>
<table>
  <thead>
  <tr>
    <th>Nome</th>
    <th>Cpf</th>
    <th>Peso</th>
    <th>Altura</th>
    <th>IMC</th>
  </tr>
  </thead>
  <tbody>
  <tr th:each="pessoa : ${pessoas}">
    <td th:text="${pessoa.nome}"></td>
    <td th:text="${pessoa.cpf}"></td>
    <td th:text="${pessoa.peso}"></td>
    <td th:text="${pessoa.altura}"></td>
    <td th:text="${pessoa.imc}"></td>
  </tr>
  </tbody>
</table>
</body>
</html>
```

---

## ▶️ Como Rodar o Projeto

### Opção 1 — Pela IDE (IntelliJ / VS Code)

1. Abra o projeto na IDE
2. Localize a classe `PessoasImcApplication.java`
3. Clique no botão **Run** (▶️) ou pressione `Shift + F10` (IntelliJ)

### Opção 2 — Pelo Terminal (Maven)

Na pasta raiz do projeto, execute:

```bash
mvn spring-boot:run
```

### Opção 3 — Gerando o JAR e executando

```bash
mvn clean package
java -jar target/pessoas-imc-0.0.1-SNAPSHOT.jar
```

---

## 🌐 Acessando a Aplicação

Com a aplicação rodando, abra o navegador e acesse:

```
http://localhost:8080/pessoas
```

Você verá a tabela com as duas pessoas cadastradas e seus respectivos IMCs calculados automaticamente.

---

## 🔢 Tabela de Classificação do IMC (referência)

| IMC | Classificação |
|---|---|
| Abaixo de 18,5 | Abaixo do peso |
| 18,5 – 24,9 | Peso normal |
| 25,0 – 29,9 | Sobrepeso |
| 30,0 – 34,9 | Obesidade Grau I |
| 35,0 – 39,9 | Obesidade Grau II |
| Acima de 40,0 | Obesidade Grau III |

---

## 🧩 Padrão MVC Utilizado

| Camada | Arquivo | Responsabilidade |
|---|---|---|
| **Model** | `Pessoa.java` | Representa os dados e calcula o IMC |
| **View** | `pessoas.html` | Exibe os dados em uma tabela HTML |
| **Controller** | `PessoaController.java` | Cria a lista de pessoas e envia para a View |
