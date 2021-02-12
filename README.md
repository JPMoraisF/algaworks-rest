
# API REST de gerenciamento de pedidos
Esta é uma pequena API Rest de gerenciamento de ordens de serviço para uma pequena loja fictícia de reparo de eletrônicos. A API foi desenvolvida utilizando o Spring Boot e um banco de dados MySQL para armazenar os pedidos.

<br/>

## O que faz?

Atualmente, o cliente da API pode cadastrar um Cliente na base de dados e uma Ordem de Serviço. Para cadastrar uma Ordem de Serviço, essa tem que estar associada a um cliente previamente cadastrado no sistema.

Por enquanto, existem duas entidades principais na API. O **Cliente**, a **Ordem de Serviço** e o **Comentario**. A entidade comentário é fortemente ligada à Ordem de Serviço.

**Cliente**
 - id
 - nome
 - e-mail
 - telefone

**Ordem Servico**
 - id
 - id_cliente
 - descrição
 - preço

**Comentário**
 - id
 - descrição
 - dataEnvio
 - id_ordem_servico

<br/>

## Endpoints da entidade Cliente:

### Cadastrar um cliente
    POST localhost:8080/api/clientes

**Um exemplo de corpo de requisição com um cliente válido para cadastro é:**

    {
	"nome": "Fulano de Tal",
	"email": "fulanodetal@exemplo.com",
	"telefone": "81999674378"	
	}

*Note que não há verificação de telefone, então qualquer string de número é aceita. Note também que embora haja uma validação de e-mail, esta apenas verifica se é um e-mail em formato válido, e não necessariamente valida se é um e-mail ou domínio existente.*

<br/>
O retorno da requisição de cadastro traz um JSON com os dados informados pelo cliente adicionado do ID criado na base de dados. Por exemplo, para a requisição acima, um retorno da API seria:

   
     {
      "id": 1,
      "nome": "Fulano de Tal",
      "email": "fulanodetal@exemplo.com",
      "telefone": "81999674378"
    }

***

### Retornar uma lista de todos os clientes cadastrados

    GET localhost:8080/api/clientes

O retorno da requisição é um array de objetos com todos  os clientes cadastrados no banco de dados.

```
[
    {
      "id": 1,
      "nome": "Fulano de Tal",
      "email": "fulanodetal@exemplo.com",
      "telefone": "81999674378"
    },
    {
        "id": 2,
	"nome": "Beltrano de Tal",
	"email": "beltranodetal@exemplo.com",
	"telefone": "81999458739"
    }
]
```

## O endpoint de cadastro de Ordem de Serviço é:

    localhost:8080/api/ordens-servico

**Um exemplo de corpo de requisição com uma ordem de serviço válida é:**

    {
    	"cliente": {
    		"id": 1
    	},
    	"descricao": "Reparo em celular Galaxy S10+",
    	"preco": 499.99
    }
    
*O ID do cliente passado no corpo dessa requisição corresponde ao ID retornado na requisição de cadastro de cliente, e  precisa corresponder a um cliente válido.*

**Retorno do endpoint de cadastro de Ordem de Serviço**
Para o exemplo acima, o retorno do cadastro de Ordem de Serviço seria:

    {
      "id": 1,
      "cliente": {
        "id": 1,
        "nome": "Fulano de Tal",
      },
      "descricao": "Reparo em celular Galaxy S10+",
      "preco": 499.99,
      "status": "ABERTA",
      "dataAbertura": "2021-01-06T22:30:21.351941-03:00",
      "dataFinalizacao": null
    }
Uma Ordem de Serviço, quando criada, é definida automaticamente com o Status de **ABERTA**, a dataAbertura com a data que a requisição foi feita e a dataFinalização em branco, para ser definida quando chamar o endpoint de encerramento de Ordem de Serviço.


