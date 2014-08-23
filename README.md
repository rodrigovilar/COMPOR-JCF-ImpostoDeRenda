COMPOR-JCF-ImpostoDeRenda
=========================

Experimento de uso do COMPOR/JCF para a implementação de um sistema de Declaração de Imposto de Renda.

Esse experimento consiste da implementação de duas versões do sistema de Declaração de Imposto de Renda.
A primeira utilizará código puramente orientado a objetos para organizar as classes do sistema. Já 
a segunda versão utilizará o COMPOR/JCF para implementar o sistema através de componentes.

O branch master deste repositório definirá os requisitos do sistema de Declaração de Imposto de Renda, 
através de testes automáticos executados sobre uma fachada comum.

O branch 'OO' implemetará a fachada e seus respectivos testes, utilizando código puramente OO.

O branch 'COMPOR' implemetará a fachada e seus respectivos testes, utilizando o COMPOR/JCF.

Cada teste de unidade escrito possui um número e está documentado através de um commit no branch master. 
O número do teste segue o formato: T_<Funcionalidade>_<Caso de teste>. A lista de funcionalidades 
testadas está no final deste documento.

Nos demais branches, cada vez que um teste é satisfeito, um commit com o número do teste deve ser
criado. Para prosseguir para o próximo teste, um merge deve ser feito o branch master, especificamente
no commit relativo ao próximo teste.

## Equipe

Esse experimento será realizado por uma equipe de seis desenvolvedores, que trocam opiniões sobre
como melhor projetar as duas versões do sistema:

* Rodrigo Vilar (@rodrigovilar) - Doutorando (UFCG) e Professor assistente (UFPB)
* Delano Oliveira (@delanohelio) - Mestrando (UFCG)
* Mestrando 1 () - Mestrando (UFCG) - Volutário
* Mestrando 2 () - Mestrando (UFCG) - Volutário
* Mestrando 3 () - Mestrando (UFCG) - Volutário
* Mestrando 4 () - Mestrando (UFCG) - Volutário


## Restrições

* As versões do sistema de Declaração de Imposto de Renda devem obedecer estritamente a fachada do 
experimento (Fachada, DTOs e Exceções). Não sendo possível alterar os DTOs, por exemplo.

* Não será testada a persistência dos dados inseridos no sistema. Eles ficarão disponíveis apenas em 
memória, pois o intuito desse experimento é verificar a influência do COMPOR no  projeto de 
componentes de um sistema.

## Estrutura do código

* O pacote net.compor possui o código do COMPOR/JCF
* O código do experimento está contido nos pacotes abaixo de br.ufcg.ppgcc.compor.ir
* O subpacote fachada contém a classe de fachada, os DTOs e as Excecoes. Esse código não pode ser 
alterado pelos branches de implementação
* O subpacote teste contém os testes automáticos que amarram os requisitos do sistema e são 
executados contra a fachada
* O subpacote deve conter todo o código necessário para a implementação de cada uma das versões do 
sistema
 
## Lista de funcionalidades testadas

1. Cadastro de titulares
1. Cadastro de Fontes pagadoras
1. Cadastro de Dependentes
1. Cálculo de imposto de renda detalhado nas cinco faixas de alíquotas
1. Dedução no imposto de renda por Dependentes
1. Imposto pago e a pagar
1. Cadastro de Gastos Dedutíveis (Educação e Saúde)
1. Dedução no imposto de renda por Gastos Dedutíveis (Educação e Saúde)
1. Cálculo de imposto de renda simplificado nas cinco faixas de alíquotas