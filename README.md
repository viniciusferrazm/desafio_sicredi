DESAFIO SICREDI

QA: Vinicius Ferraz do Amaral Mello
Cargo: Analista de Testes e Automação Pleno


As regras seguidas podem ser encontradas aqui: https://github.com/rh-southsystem/Sicredi-Digital-QA

Considerações sobre os desafio que foram encontradas durante os testes:

- Inserindo uma simulação com CPF de forma incorreta, por exemplo “123” ou "16458904578543", o comportamento deveria ser de não realizar a inserção. Porém, é possível inserir a simulação.
- Inserindo uma simulação com CPF duplicado, deveria retornar um HTTP Status 409. Porém está retornando 400.
- É possível inserir uma simulação com número de parcelas maior que 48, quebrando a regra.
- Consultar todas as simulações quando não existe nenhuma cadastrada, deveria estar retornando 204. Porém retornou 200.
- Não foi possível alterar o valor de uma simulação já cadastrada.
- Todos os ID`s utilizados para remoção, mesmo que inexistentes estão passando como OK e retornando 200. Com isso não foi possível testar a remoção de uma simulação com ID não existente.
- A remoção de simulação por ID deveria rertornar 204. Porém está retornando 200.


Instruções para o projeto:

- Clonar o projeto da API
- Executar a API utilizando o comando: mvn clean spring-boot:run

- Clonar o projeto utilizado para os testes
- Executar os cenários
- Clicar sobre o cenário de teste com o botão direito e clicar em Run
