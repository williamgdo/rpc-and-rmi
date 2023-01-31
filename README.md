# rpc-and-rmi
A file transfer system using one system using RPC in C language and another using RMI in Java
Um sistema de transferência de arquivo, usando RPC na linguagem C e RMI em Java

## RMI (Remote Method Invocation)

- Para compilar os arquivos java, use o comando: 
`javac uploadRmi/Upload.java uploadRmi/Server.java uploadRmi/Client.java` 
de dentro da pasta /rmi

- Para executar o projeto, é necessário três passos **na ordem descrita**:

1. Executar `rmiregistry` na pasta /rmi;

2. Executar `java uploadRmi.Server`;

3. Executar `java uploadRmi.Client <nome_do_arquivo> <host>`, substituindo <nome_do_arquivo> pelo nome de um arquivo que esteja na pasta rmi e <host> pelo endereco do host. Para testes simples, um arquivo *text.txt* foi providenciado.


## Script para gerar arquivos de tamanhos específicos:

```bash
touch file-1gb file-100mb file-5mb file-1mb
shred -n 1 -s 1G file-1gb
shred -n 1 -s 100M file-100mb
shred -n 1 -s 5M file-5mb
shred -n 1 -s 1M file-1mb
```