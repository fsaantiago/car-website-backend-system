### isão Geral do Código: Serviço de Localização (Boogle Maps)

Dentro da pasta `boogle-maps`, você encontrará o código pertinente ao serviço de localização. Este serviço funciona como um Mock para replicar um WebService de Mapas. Ele opera sob a premissa de que, ao ser fornecido com uma latitude e longitude, ele fornecerá um endereço aleatório.

Embora você não seja obrigado a implementar nenhuma implementação como parte deste aplicativo, vamos examinar brevemente os arquivos incluídos. Vale ressaltar que cada pacote reside dentro de`com.udacity`; portanto, omitiremos essa parte do nome do pacote nas descrições abaixo.

<img src="/images/boogle-map.png">

## Boogle Maps

`boogle.maps`

#### Endereço:

Esta classe define a entidade de Endereço, composta principalmente por variáveis privadas como endereço, cidade, estado e código postal. Notavelmente, a latitude e a longitude não são armazenadas aqui; elas são obtidas da API de Veículos.


#### BoogleMapsApplication:

Esta classe inicializa o Boogle Maps como um aplicativo Spring Boot.

#### MapsController:

Este serve como o controlador REST real para o aplicativo. Ele lida com solicitações `GET` e responde de acordo. Em nosso cenário, sendo um Mock de um WebService, ele simplesmente retorna um endereço aleatório do repositório.

#### MockAddressRepository:

Os repositórios geralmente oferecem persistência de dados enquanto o serviço web está operacional. Neste caso, este repositório Mock seleciona um endereço aleatório da matriz `ADDRESSES` definida no arquivo.



