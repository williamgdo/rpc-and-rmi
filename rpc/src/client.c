#include <stdio.h>
#include <stdlib.h>
#include <rpc/rpc.h>
#include "file.h"

int main (int argc, char **argv){
	CLIENT *cl;

	char *server;
	char buffer[1204];
	

	int *result;
	int input;

	if (argc != 3) {
		fprintf(stderr, "usage: %s <hostname> <number>\n", argv[0]);
		exit(1);
	}

	server = argv[1];

	if ((cl = clnt_create(server, FILE_PROG, FILE_VERS, "tcp")) == NULL) {
		clnt_pcreateerror(server);
		exit(2);
	}

	scanf("digite numero: %d", &input);
	if ( (result = fileproc_1(&input, cl)) == NULL) {
		clnt_perror(cl, server);
		exit(3);
	}

	printf("result: %d\n", *result);

	clnt_destroy(cl);

	exit(0);

}
