## Eureka Server

O Servidor Eureka é um aplicativo que contém as informações sobre todas as aplicações cliente-serviço. Cada microsserviço se registra no servidor Eureka e o servidor Eureka conhece todas as aplicações cliente em execução em cada porta e endereço IP.

<img src="/images/eureka-server.png">

### Details

- __EurekaServerApplication__: A classe principal que executa a aplicação do Servidor Eureka. Ele possui  `@EnableEurekaServer` para habilitá-lo como Servidor Eureka.

- __resoures\applications.properties__: Possui as seguintes propriedades para o Eureka server:
````propriedades
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=ON
logging.level.com.netflix.discovery=ON
````

### Executar a aplicação

Abaixo estão os comandos para construir e executar este projeto.
```
$ mvn clean package
```

```
$ java -jar target/eurekaServer-0.0.1-SNAPSHOT.jar
```

Você também pode importá-lo como um projeto Maven em sua IDE preferida e executar a classe `EurekaServerApplication`.

