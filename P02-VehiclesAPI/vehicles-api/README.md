# Vehicles API

Uma API REST para manter dados de veículos e fornecer uma visão completa dos detalhes do veículo, incluindo preço e endereço.

### VehiclesApiApplication
Isso inicia a API de Veículos como uma aplicação Spring Boot. Além disso, configura alguns fabricantes de carros no `ManufacturerRepository` e cria clientes web para se conectar aos serviços de Maps e Pricing.

<img src="/images/vehicle-api.jpeg">

`vehicles.api`

### API Error
Define métodos para retornar rapidamente erros e outras mensagens da API de Veículos.

### CarController
Este é o controlador REST real para o aplicativo. Ele lida com solicitações `GET`, `POST`, `PUT`, and `DELETE` (usando métodos no `CarService`) e determina como elas são respondidas (incluindo formatação com `CarResourceAssembler`). Você implementará esses métodos em seu código.

### CarResourceAssembler
Isso auxilia na mapeamento do `CarController` para a classe `Car` para facilitar o retorno da resposta da API.

### ErrorController
Isso ajuda a lidar com quaisquer argumentos inválidos fornecidos à API.

`vehicles.client.maps`

### Address
Similar ao arquivo `Address` para boogle-maps, isso define uma classe para uso com o `MapsClient`.

### MapsClient
Manages the format of a GET request to the `boogle-maps` WebClient to retrieve location data.

`vehicles.client.prices`

### Price
Similar ao arquivo `Price` para `pricing-service`, isso declara uma classe para uso com o PriceClient.

### PriceClient
Gerencia o formato de uma solicitação GET para o WebClient `pricing-service` para recuperar dados de localização.

`vehicles.domain`

### Condition
Lista os valores disponíveis para a condição de um carro (New oBoogle).

### Location
Declara informações sobre a localização de um veículo. Isso difere da classe Address usada pelo `boogle-maps`, focando principalmente em armazenar valores de latitude e longitude. Como os dados coletados do boogle-maps, como `address`, são anotados como `@Transient`, esses dados não são armazenados até a próxima vez que o `boogle-maps` for chamado.

`vehicles.domain.car`
### Car
Define informações específicas sobre um determinado veículo, principalmente detalhes sobre a própria entrada do carro (como `CreatedAt`). Observe que uma classe separada, `Details`, também armazena detalhes adicionais sobre o carro específicos de fabricante, cor e modelo. Similar à  `Location` com dados como endereço, isso usa uma tag `@Transient` com `price`, exigindo uma chamada ao Serviço de Preços sempre que um preço for desejado.

### CarRepository
Este repositório fornece persistência de dados enquanto o serviço web está operacional, principalmente para informações de veículos recebidas no `CarService`.

### Details
Especifica detalhes adicionais do veículo, principalmente relacionados à construção do carro em si, como tipo de `fuelType` e `mileage`.

`vehicles.domain.manufacturer`
### Manufacturer
Defines the Manufacturer class, primarily consisting of an ID code and manufacturer name.

### ManufacturerRepository
This repository provides data persistence while the web service is operational, primarily for storing manufacturer information like that initialized in `VehiclesApiApplication`.

`vehicles.domain`
### CarNotFoundException
Creates a `CarNotFoundException` that can be thrown when an issue arises in the `CarService`.

### CarService
O Serviço de Carro realiza grande parte do trabalho do código. Ele pode recuperar toda a lista de veículos ou um único veículo por ID (incluindo chamadas aos clientes web de maps e pricing). Também pode salvar informações de veículo atualizadas e excluir um carro existente. Todas essas ações são iniciadas pelo CarController com base em consultas à API REST. Você implementará a maioria desses métodos por conta própria.

`test/../vehicles.api`
### CarControllerTest
Aqui, vários métodos realizados pelo CarController são testados criando chamadas de mock para a API de Veículos. Você implementará alguns desses métodos por conta própria para praticar na construção de seus próprios testes.

### Vehicles API Instructions
Neste projeto, você criará uma API de Veículos baseada em REST que se comunica com um serviço de localização e preços usando o Spring Boot. Além disso, você converterá a API de Serviços de Preços existente em um microserviço registrado em um servidor Eureka. Siga as [instruções do README e do código](https://github.com/fsaantiago/car-website-backend-system/blob/main/P02-VehiclesAPI/vehicles-api/README.md) e certifique-se de ter concluído todos os itens conforme a rubrica.

### Convert the Pricing Service
Transforme o Serviço de Preços em um microserviço registrado em um servidor Eureka. Além disso, adicione um teste adicional para o microserviço.

### Test and Document the Vehicles API
Adicione testes para a classe CarController.
Utilize o Swagger para implementar a documentação da API para a API de Veículos.

### Executando as aplicações
Observe que as aplicações Boogle Maps, Pricing Service e Vehicles API devem estar em execução para que as operações sejam realizadas corretamente, embora possam ser iniciadas de forma independente.

Você pode usar cada uma delas em janelas separadas em sua IDE favorita ou usar os seguintes comandos:

1. `$ mvn clean package` (execute isso em cada diretório que contém as aplicações separadas)

2. Boogle Maps:
```
$ java -jar target/boogle-maps-0.0.1-SNAPSHOT.jar
```

O serviço está disponível por padrão na porta 9191. Você pode verificar isso no terminal usando `$ curl http://localhost:9191/maps\?lat\=20.0\&lon\=30.0`

Pricing Service:
```
$ java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

Vehicles API:
```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```
Quando a documentação da API Swagger estiver implementada, estará disponível em: http://localhost:8080/swagger-ui.html

## Features

- API REST explorando os principais verbos HTTP e recursos
- Hateoas
- Manipulação de erros personalizada da API usando ControllerAdvice
- Documentação da API Swagger
- HTTP WebClient
- Teste MVC
- Mapeamento automático de modelo

## Instructions

#### Executando o código

Para executar corretamente esta aplicação, você precisa iniciar primeiro a Orders API e a Service API.

```
$ mvn clean package
```

```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```

Importe-o em sua IDE favorita como um projeto Maven.

## Operações

Swagger UI: http://localhost:8080/swagger-ui.html

### Crie um veículo

`POST` `/cars`

```json
{
  "condition": "USED",
  "details": {
    "body": "sedan",
    "model": "Impala",
    "manufacturer": {
      "code": 101,
      "name": "Chevrolet"
    },
    "numberOfDoors": 4,
    "fuelType": "Gasoline",
    "engine": "3.6L V6",
    "mileage": 32280,
    "modelYear": 2018,
    "productionYear": 2018,
    "externalColor": "white"
  },
  "location": {
    "lat": 40.73061,
    "lon": -73.935242
  }
}
```

### Recupere um Vehicle

`GET` `/cars/{id}`

Este recurso recupera os dados do Veículo do banco de dados
e acessa o Pricing Service e o Boogle Maps para enriquecer
as informações do Veículo a serem apresentadas

### Atualize o veículo

`PUT` `/cars/{id}`

```json
{
  "condition": "USED",
  "details": {
    "body": "sedan",
    "model": "Impala",
    "manufacturer": {
      "code": 101,
      "name": "Chevrolet"
    },
    "numberOfDoors": 4,
    "fuelType": "Gasoline",
    "engine": "3.6L V6",
    "mileage": 32280,
    "modelYear": 2018,
    "productionYear": 2018,
    "externalColor": "white"
  },
  "location": {
    "lat": 40.73061,
    "lon": -73.935242
  }
}
```

### Apague o veículo

`DELETE` `/cars/{id}`


