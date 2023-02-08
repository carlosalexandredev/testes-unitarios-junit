# Testes

[![Linkedin Badge](https://img.shields.io/badge/-Carlos%20Alexandre-002E74?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlosalexandredev/)](https://www.linkedin.com/in/carlosalexandredev/)
[![Gmail Badge](https://img.shields.io/badge/-carlosalexandredev.contato@gmail.com-002E74?style=flat-square&logo=Gmail&logoColor=white&link=mailto:carlosalexandredev.contato@gmail.com)](mailto:carlosalexandredev.contato@gmail.com)

## Pirâmide de  Teste

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

## JUnit

JUnit é uma biblioteca de testes unitários para Java. Ele permite escrever testes automatizados para verificar se o
código está funcionando corretamente. Os testes escritos com JUnit seguem uma estrutura padrão que inclui a anotação *
*@Test** para indicar que um método é um teste, e as asserções para verificar se o resultado esperado é igual ao
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

**Documentação:** [JaCoCo](https://www.jacoco.org/jacoco/trunk/index.html)
