# Sistema de Gerenciamento de Inventário

Este é um sistema simples de gerenciamento de inventário em Java, que permite cadastrar, listar, editar, buscar e excluir produtos.

## Funcionalidades
- **Adicionar Produto**: Cadastrar novos produtos no inventário.
- **Listar Produtos**: Exibir todos os produtos cadastrados, com ou sem filtragem por categoria.
- **Filtrar Produtos**: Filtrar produtos por categoria, nome, quantidade ou preço.
- **Atualizar Produto**: Editar informações de um produto existente.
- **Excluir Produto**: Remover um produto do inventário.

## Tecnologias Utilizadas
- **Java**: Linguagem principal do projeto.
- **Maven**: Para buildar o projeto e gerenciar as dependências.
- **Jackson**: Biblioteca para manipulação de JSON.
- **Visual Studio Code**: IDE utilizada para desenvolvimento e execução do código.
- **Debian**: Sistema Operacional utilizado durante o desenvolvimento.

## Pré-requisitos
Antes de rodar a aplicação, certifique-se de ter:
1. Java Development Kit (JDK) 17 ou superior instalado.
2. Uma IDE como Visual Studio Code, IntelliJ IDEA, Eclipse, ou NetBeans.
3. Maven instalado.

## Como Rodar a Aplicação Localmente
Siga as instruções abaixo para executar a aplicação:

### Passo 1: Clonar o Repositório
```bash
git clone https://github.com/netorapg/desafioAgilStoreGerenciamento.git
```
### Passo 2: Entre no diretório
```bash
cd desafioAgilStoreGerenciamento
```
### Passo 3: Compile o projeto com Maven
```bash
mvn clean package
```
### Passo 4: Execute o projeto
```bash
java -jar target/agilstore-1.0-SNAPSHOT.jar
```
