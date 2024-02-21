<div align="center">
  <h1>🌿💜 API para Sistema de Avaliação de Créditos 💜🌿</h1>
  <p>Uma empresa de empréstimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma <strong>API REST SPRING BOOT E KOTLIN</strong> para a empresa fornecer aos seus clientes as seguintes funcionalidades:</p>
</div>

## Cliente (Customer):
- **Cadastrar:**
   1. **Request:** *firstName, lastName, cpf, income, email, password, zipCode e street*
   2. **Response:** *String*
- **Editar cadastro:**
   1. **Request:** *id, firstName, lastName, income, zipCode, street*
   2. **Response:** *firstName, lastName, income, cpf, email, income, zipCode, street*
- **Visualizar perfil:**
   1. **Request:** *id*
   2. **Response:** *firstName, lastName, income, cpf, email, income, zipCode, street*
- **Deletar cadastro:**
   1. **Request:** *id*
   2. **Response:** *sem retorno*

## Solicitação de Empréstimo (Credit):
- **Cadastrar:**
   1. **Request:** *creditValue, dayFirstOfInstallment, numberOfInstallments e customerId*
   2. **Response:** *String*
- **Listar todas as solicitações de empréstimo de um cliente:**
   1. **Request:** *customerId*
   2. **Response:** *creditCode, creditValue, numberOfInstallment*
- **Visualizar um empréstimo:**
   1. **Request:** *customerId e creditCode*
   2. **Response:** *creditCode, creditValue, numberOfInstallment, status, emailCustomer e incomeCustomer*

<div align="center">
  <img src="https://i.imgur.com/7phya16.png" height="450" width="650" alt="API para Sistema de Avaliação de Créditos"/><br>
  <p>Diagrama UML Simplificado de uma API para Sistema de Avaliação de Crédito</p>
</div>

<div align="center">
  <img src="https://i.imgur.com/1Ea5PH3.png" height="350" width="600" alt="Arquitetura em 3 camadas Projeto Spring Boot"/><br>
  <p>Arquitetura em 3 camadas Projeto Spring Boot</p>
</div>

## DESAFIO (Implementado)
Implemente as regras de negócio a seguir para a solicitação de empréstimo:
1. o máximo de parcelas permitido será 48
2. data da primeira parcela deverá ser no máximo 3 meses após o dia atual

---

## Links Úteis
- [Spring Initializr](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=3.0.3&packaging=jar&jvmVersion=17&groupId=me.dio&artifactId=credit-application-system&name=credit-application-system&description=Credit%20Application%20System%20with%20Spring%20Boot%20and%20Kotlin&packageName=me.dio.credit-application-system&dependencies=web,validation,data-jpa,flyway,h2)
- [Common application properties](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/common-application-properties.html)
- [Versionar sua base de dados com Spring Boot e Flyway](https://medium.com/cwi-software/versionar-sua-base-de-dados-com-spring-boot-e-flyway-be4081ddc7e5)
- [Todas as anotações do JPA: Anotações de mapeamento](https://strn.com.br/artigos/2018/12/11/todas-as-anota%C3%A7%C3%B5es-do-jpa-anota%C3%A7%C3%B5es-de-mapeamento/)
- [Objeto de Transferência de Dados](https://pt.wikipedia.org/wiki/Objeto_de_Transfer%C3%AAncia_de_Dados)
- [CRUD](https://pt.wikipedia.org/wiki/CRUD)
- [Spring Data JPA - Repository Query Keywords](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords)
- [Spring Data JPA - At Query](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query)
- [Spring Data JPA - Glossary](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#glossary)

---

##  Projeto Original
### Camila Cavalcante
https://github.com/cami-la/credit-application-system<br>
[![Linkedin Badge](https://img.shields.io/badge/-Camila-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/cami-la/)](https://www.linkedin.com/in/cami-la/)
[![Gmail Badge](https://img.shields.io/badge/-camiladsantoscavalcante@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:camiladsantoscavalcante@gmail.com)](mailto:camiladsantoscavalcante@gmail.com)

---

## Contribuindo

Este repositório foi criado para fins de estudo, então contribua com ele.<br>
Se te ajudei de alguma forma, ficarei feliz em saber. E caso você conheça alguém que se identifique com o conteúdo, não
deixe de compartilhar.
<br>
Se possível:
- ⭐️ Star o projeto
- 🐛 Encontrar e relatar issues
