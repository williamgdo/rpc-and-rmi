#Makefile para compilação do RPC

CC := gcc
LD_FLAG := -ltirpc
INCLUDE_FLAG := -I/usr/include/tirpc/


build: objects
	mkdir -p bin
	$(CC) $(LD_FLAG) -o bin/client artifacts/file_clnt.o  artifacts/client.o
	$(CC) $(LD_FLAG) -o bin/server artifacts/file_svc.o  artifacts/server.o

rpcgen:
	mv src/file.x .
	rpcgen -C file.x
	mv file* src/

objects: rpcgen
	mkdir -p artifacts
	$(CC) $(INCLUDE_FLAG) -c src/file_clnt.c -o artifacts/file_clnt.o
#	$(CC) $(INCLUDE_FLAG) -c src/file_xdr.c -o artifacts/file_xdr.o
	$(CC) $(INCLUDE_FLAG) -c src/file_svc.c -o artifacts/file_svc.o
	$(CC) $(INCLUDE_FLAG) -c src/client.c -o artifacts/client.o
	$(CC) $(INCLUDE_FLAG) -c src/server.c -o artifacts/server.o
	
clean:
	rm -rf bin artifacts src/file_clnt.c src/file_svc.c src/file_xdr.c src/file.h
