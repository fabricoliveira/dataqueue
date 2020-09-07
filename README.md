# Data Queue

## Objetivo

Criei esse projeto com o objetivo de aprender um pouco sobre ActiveMQ.

## Como utilizar o programa

- Você deve baixar e manter o ActiveMQ rodando na sua máquina, durante a execução do programa.
Para mais informações, acesse o site do [ActiveMQ](https://activemq.apache.org/getting-started).

- Todos os arquivos de entrada de dados, devem ser colocados no diretório padrão HOMEPATH/data/in

        Exemplo de conteúdo total do arquivo: 
        001ç1234567891234çPedroç50000 
        001ç3245678865434çPauloç40000.99 
        002ç2345675434544345çJose da 
        SilvaçRural 
        002ç2345675433444345çEduardo 
        PereiraçRural 
        003ç10ç[1-10-100,2-30-2.50,3-40-3.10]ç 
        Pedro 
        003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

- Os dados serão processados e salvos, no diretório padrão HOMEPATH/data/out, num arquivo com o mesmo nome e extensão do arquivo de entrada.
    
        Exemplo do arquivo de saída de dados:
        Quantidade de clientes no arquivo de entrada: [informação]
        Quantidade de vendedores no arquivo de entrada: [informação]
        ID da venda mais cara: [informação]
        O pior vendedor: [informação]

## Tecnologias utilizadas

- O programa foi desenvolvido utilizando tecnologias baseadas em Java.

- Maven para a gerenciar as dependências e realizar o build do programa.

- GIT para o versionamento do código.

- ActiveMQ para a fila de mensagens.

- Gson para transformar os dados em JSON/Objeto.

- Quartz Job Scheduler para controlar o tempo que o programa busca informações em disco.

#### Considerações:

- Utilizar no mínimo a versão 8 do Java.