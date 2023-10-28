
# Projeto de Mini Aplicação de E-commerce (Nome do Projeto)

## Descrição do Projeto

Este projeto é uma mini aplicação de e-commerce que fornece uma API RESTful para gerenciar produtos, clientes e pedidos. Foi desenvolvido em Spring Boot, utilizando Java 17, com recursos de segurança implementados usando o Spring Security para a criação de tokens que validam permissões de acesso aos endpoints. Além disso, o projeto inclui uma documentação completa da API, disponibilizada via Swagger, facilitando o uso e a compreensão dos endpoints e recursos oferecidos. O projeto está hospedado na plataforma Railway para possibilitar o acesso externo.

## Diagrama de classe

``` mermaid
classDiagram
  class Produto {
    id: long
    nome: string
    preco: bigdecimal
    ean: string
    qtUnit: long
    estoque: long
  }

  class Endereco {
    id: long
    endereco: string
    complemento: string
    numero: string
    cep: string
  }

  class Telefone {
    id: long
    ddi: string
    ddd: string
    numero: string
  }

  class Cliente {
    id: long
    nome: string
    email: string
    status: enum
    enderecos: Endereco[]
    telefones: Telefone[]
    limite: bigdecimal
  }

  class Pedido {
    cliente: Cliente
    items: ItemPedido[]
  }

  class ItemPedido {
    id: long
    produto: Produto
    quantidade: long
  }

  Produto --|> ItemPedido
  Cliente "1" --o "n" Endereco : tem
  Cliente "1" --o "n" Telefone : tem
  Pedido "1" --o "n" ItemPedido : contém

```

## Recursos e Funcionalidades

-   **Gestão de Produtos:** Adicione, atualize, consulte e exclua produtos.

-   **Gestão de Clientes:** Registre novos clientes, atualize informações e gerencie endereços e informações de contato.

-   **Gestão de Pedidos:** Crie, atualize, consulte e exclua pedidos, incluindo informações sobre os produtos comprados e quantidades.


## Tecnologias Utilizadas

-   Java 17
-   Spring Boot
-   Spring Security
-   Swagger (para documentação da API)
-   Railway (para hospedagem externa)

## Como Usar

1.  **Clonagem do Repositório:**
    ``` shell
    git clone https://github.com/Diego34ra/projeto-ecommerce.git
    ``` 
2.  **Compilação e Execução:**

    ``` shell
    mvn spring-boot:run
    ``` 

3.  **Documentação da API:** Acesse a documentação da API em [URL_do_Swagger].

4.  **Implantação Externa:** O projeto está hospedado no Railway, e você pode acessá-lo em [URL_do_Projeto].


## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novos recursos. Você pode abrir problemas (issues) ou enviar solicitações de pull (pull requests) para contribuir para o projeto.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter detalhes.

## Contato

-   Autor: Diego Ribeiro Araújo
-   E-mail: diegoribeiro13ra@hotmail.com
