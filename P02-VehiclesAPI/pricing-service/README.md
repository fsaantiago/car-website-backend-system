## Código do Serviço de Preços

Você pode encontrar o código do microserviço de serviço de preços na pasta `pricing-service`. Ele funciona como um WebService REST que simula um backend para armazenar e microserviçar o preço de um veículo especificado. No projeto, você o converterá em um microserviço registrado por meio de um servidor Eureka.

Vamos inspecionar brevemente os arquivos incluídos, dos quais você só precisará implementar alguns. Observe que cada pacote está dentro de `com.udacity`.

<img src="/images/price-check.png">

`pricing`

### PricingServiceApplication
Isso inicializa o Serviço de Preços como uma aplicação Spring Boot.

`pricing.api`

### PricingController
Este serve como o controlador REST real para o aplicativo. Ele determina a resposta a uma solicitação GET, que neste caso é um preço gerado aleatoriamente obtido do `PricingService`. Uma vez transformado em um microserviço, o Controlador pode não ser explicitamente necessário.

`pricing.domain.price`

### Price
Isso define a classe Price, consistindo principalmente de variáveis privadas `currency`, `price` e `vehicleId`.

### PriceRepository
Este repositório fornece uma forma de persistência de dados enquanto o serviço web está operacional, especificamente a associação de ID ao preço gerado pelo `PricingService`.

`pricing.service`

### PriceException
Isso cria uma `PriceException` que pode ser lançada quando ocorre um problema no `PricingService`.

### PricingService
O Serviço de Preços realiza a maior parte das operações do código. Aqui, ele estabelece um mapeamento de preços aleatórios para IDs, juntamente com o método para gerar esses preços aleatórios. Uma vez transformado em um microserviço, o Serviço pode não ser explicitamente necessário.
