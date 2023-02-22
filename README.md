# Testes

[![Linkedin Badge](https://img.shields.io/badge/-Carlos%20Alexandre-002E74?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlosalexandredev/)](https://www.linkedin.com/in/carlosalexandredev/)
[![Gmail Badge](https://img.shields.io/badge/-carlosalexandredev.contato@gmail.com-002E74?style=flat-square&logo=Gmail&logoColor=white&link=mailto:carlosalexandredev.contato@gmail.com)](mailto:carlosalexandredev.contato@gmail.com)

## Pirâmide de Teste

A Pirâmide de Teste é uma representação visual que ilustra a proporção recomendada entre os diferentes tipos de testes
que devem ser usados em um projeto de software. A base da pirâmide é formada por testes automatizados de unidade, que
são os mais comuns e devem ser os mais numerosos. Eles testam pequenas partes do código, geralmente funções ou métodos
individuais, e são executados rapidamente.

Acima dos testes de unidade, há menos testes de integração, que testam como as diferentes partes do código funcionam
juntas. Eles são mais lentos e complexos do que os testes de unidade.

No topo da pirâmide, há testes de sistema e testes de aceitação, que testam o software como um todo, incluindo sua
interação com outros sistemas e sua capacidade de atender às necessidades do usuário. Eles são os menos comuns e os mais
caros de serem executados.

A idéia principal da pirâmide é que, quanto mais baixo na pirâmide, mais testes devem ser escritos, e quanto mais alto,
menos testes são necessários. Isso ajuda a equilibrar a cobertura dos testes com o custo e a complexidade de escrever e
executar esses testes.

![piramide.webp](/piramide.webp)

## FIRST

O princípio **FIRST** é um conjunto de boas práticas para escrever testes unitários. Ele é composto por quatro regras:

- **FAST:** os testes devem ser rápidos de executar, para que possam ser rodados com frequência.
- **INDEPENDENT:** os testes devem ser independentes uns dos outros, para que possam ser executados em qualquer ordem.
- **REPEATABLE:** os testes devem ser reproduzíveis, independentemente do ambiente em que são executados.
- **SELF-VALIDATING:** os testes devem ter uma saída clara e objetiva, facilitando a validação dos resultados.
- **TIMELY:** os testes devem ser escritos ao mesmo tempo que o código é escrito, para que possam ser usados para
  validar as mudanças no código.

## Behavior-Driven Development (BDD)

BDD significa "Desenvolvimento Orientado a Comportamento" (Behavior-Driven Development, em inglês). É uma abordagem para
o desenvolvimento de software que se concentra na entrega de valor para o negócio e nas expectativas dos usuários, em
vez de se concentrar apenas em funcionalidades técnicas. BDD é uma evolução do TDD (Test-Driven Development) e se
concentra em descrever o comportamento esperado do software antes da implementação.

O BDD utiliza uma linguagem natural para descrever os comportamentos do sistema, de forma a tornar o processo de
desenvolvimento mais colaborativo e compreensível para todas as partes interessadas, incluindo desenvolvedores, testers,
gerentes de produto e stakeholders. O processo de BDD começa com uma discussão sobre o comportamento desejado do
software, seguida da escrita de casos de teste para validar esse comportamento. Em seguida, os desenvolvedores
implementam o software para atender a esses casos de teste, e os testes são executados para validar se o software
corresponde ao comportamento esperado.

O objetivo do BDD é assegurar que o software atenda aos requisitos do negócio e as expectativas dos usuários, ao mesmo
tempo em que garante que o software seja testável e confiável. Isso ajuda a evitar o desperdício de tempo e recursos em
funcionalidades que não são realmente valiosas para o negócio ou para os usuários.

No Java, a implementação do padrão de nomenclatura BDD pode ser realizada usando frameworks de teste BDD, como o
JBehave, Cucumber ou Spek. Esses frameworks permitem escrever casos de teste BDD em linguagem natural e depois mapear
esses casos de teste para implementações em Java.

## Tipos de testes de software

Existem vários tipos de testes de software, e eles podem ser classificados de diversas maneiras, dependendo do critério
utilizado. Aqui estão alguns tipos comuns:

Testes de unidade: verificam a funcionalidade individual de cada parte do código (módulos, funções, classes, etc.),
geralmente realizados pelos desenvolvedores.

Testes de integração: verificam a interação entre diferentes partes do sistema, para garantir que elas trabalhem juntas
de maneira adequada.

Testes de sistema: verificam se o sistema como um todo atende aos requisitos e especificações do cliente.

Testes de aceitação: verificam se o sistema está pronto para ser entregue ao cliente, validando se atende às
expectativas e requisitos especificados.

Testes de regressão: verificam se mudanças realizadas em um sistema não causaram efeitos colaterais em funcionalidades
existentes.

Testes de desempenho: verificam a capacidade do sistema de lidar com grandes volumes de dados ou usuários, testando sua
escalabilidade e eficiência.

Testes de segurança: verificam a proteção do sistema contra possíveis ameaças de invasões ou ataques.

Testes exploratórios: testes que são realizados sem um roteiro pré-definido, a fim de descobrir possíveis falhas ou
comportamentos inesperados no sistema.


___

## JUnit

JUnit é uma biblioteca de testes unitários para Java. Ele permite escrever testes automatizados para verificar se o
código está funcionando corretamente. Os testes escritos com JUnit seguem uma estrutura padrão que inclui a anotação *
*@Test* para indicar que um método é um teste, e as asserções para verificar se o resultado esperado é igual ao
resultado atual. JUnit também oferece recursos adicionais, como suporte para testes de tempo limite, testes de exceção e
testes parametrizados. Ele é amplamente utilizado em desenvolvimento de software para garantir a qualidade do código e
facilitar o processo de manutenção.

**Documentação:** [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

## Mockito

O Mockito é uma biblioteca de teste para Java que permite criar mocks (objetos simulados) para simular comportamentos
esperados em testes unitários. Com o uso de mocks, é possível testar o comportamento de uma classe isoladamente, sem a
necessidade de depender de outras partes da aplicação. Isso ajuda a garantir que os testes sejam mais precisos,
confiáveis e rápidos, e também permite que você teste cenários complexos ou excepcionais que seriam difíceis de replicar
em um ambiente de teste real.

**Documentação:** [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)

## JaCoCo

O JaCoCo é uma ferramenta de cobertura de código para Java que permite medir a quantidade de código que é executada
durante os testes. Ele gera relatórios detalhados sobre a cobertura de código, incluindo informações sobre quais linhas
foram executadas e quantas vezes, bem como quais partes do código não foram testadas. Isso ajuda a identificar lacunas
na cobertura de testes, o que pode ser útil na identificação de bugs e no aumento da qualidade do código. Além disso, o
JaCoCo também pode ser usado como uma medida de qualidade do software, já que altos níveis de cobertura de testes
geralmente indicam um software de alta qualidade.

![JACOCO.png](/JACOCO.png)

**Documentação:** [JaCoCo](https://www.jacoco.org/jacoco/trunk/index.html)

## AssertJ

AssertJ é uma biblioteca Java para testes de unidade que fornece uma sintaxe fluente e fácil de usar para escrever
testes. Ele foi projetado para ajudar a tornar os testes mais claros, legíveis e fáceis de entender, melhorando assim a
qualidade e a manutenibilidade do seu código.

Algumas das principais vantagens de usar o AssertJ incluem:

- Sintaxe clara e legível: a sintaxe fluente do AssertJ ajuda a tornar os testes mais legíveis e fáceis de entender,
  especialmente em equipes com muitos desenvolvedores.

- Suporte a vários tipos de dados: o AssertJ fornece suporte a vários tipos de dados, incluindo primitivos, objetos,
  matrizes, coleções e muito mais. Isso significa que você pode escrever testes que verifiquem a igualdade, a ordem, a
  presença ou a ausência de elementos e outras propriedades importantes.

- Ferramentas para testar exceções: o AssertJ oferece ferramentas fáceis de usar para testar exceções, garantindo que
  suas classes lancem as exceções corretas em situações específicas.