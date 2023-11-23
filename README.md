# Processo de Seleção Desenvolvedor FullStack

## Desafio 1: Programação Back-end
Neste desafio eu criei um API REST para devolver os dados de fornecedores, e ainda executar operações de criação, delecã́o e atualização dos dados.

### Tecnologias Utilizadas

* Jakarta EE (JAX-RS)
	* Utilizei Jersey como implementação da especificação JAX-RS.
* Camada de persistência (JPA + Hibernate);
* Maven
* Banco de dados MySQL
* Grizzly (Servidor)
* Controle de versão (Git);

<b>Dúvida:</b> O formato do cnpj informado no objeto da premissa 2 está incorreto.

<b>Decisão tomada:</b> Considerei que o formato foi indicado dessa forma para avaliar como seria feita a validação do mesmo. 

## Desafio 2: Programação Front-end
Neste desafio eu criei uma interface para apresentar os dados de fornecedores, requisitando os mesmo a partir da API REST criada no desafio 1. A interface permite realizar operações de criação, edição e exclusão de fornecedores. 

### Tecnologias Utilizadas

* Bootstrap
* AngularJS (versão 1.8)
* Bibliotecas:
	* Sweetalert.js para exibir alertas
	* Cleave.js para aplicar mascára ao campo de cnpj.
* Controle de versão (Git);

## Desafio 3: Lógica e Programação

O desafio foi realizado em Java e durante sua implementação surgiu a seguinte dúvida em relação a primeira premissa.

<b>Dúvida:</b> A premissa 1 está correta? Visto que o valor do ângulo indicado para 00:15h não varia da forma esperada em relação a um relógico real. 

* Premissa:
	* 00:00h possui um ângulo de 0º.
	* 00:15h possui um ângulo de 45º.
	* 00:30h possui um ângulo de 180º


<b>Decisão Tomada:</b> Assumi que houve um erro de digitação e tomei como verdade o observado em um relógio real:

 * Premissa atualizada:
	* 00:00h possui um ângulo de 0º.
	* 00:15h possui um ângulo de 90º.
	* 00:30h possui um ângulo de 180º

 ## Banco de dados

 O arquivo Dump20230905.sql contém um dump do banco de dados criados para o desafio 1 e 2. Contém 4 fornecedores cadastrados. 





