#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <rpc/rpc.h>
#include "file.h"

int main (int argc, char **argv){
	CLIENT *cl;

	FILE * filep;
	char *server;

	int i;
	

	int result = 0;
	
	int * ret;
	file_in input;

	for (i = 0; i < 4096; i++){
		input.buffer[i] = 0;
	}

	if (argc != 3) {
		fprintf(stderr, "usage: %s <hostname> <filename>\n", argv[0]);
		exit(1);
	}

	server = argv[1];

	input.file_name = (char *)calloc((strlen(argv[2])+4), sizeof(char));
	strcpy(input.file_name, argv[2]);
	if ((cl = clnt_create(server, FILE_PROG, FILE_VERS, "tcp")) == NULL) {
		clnt_pcreateerror(server);
		exit(2);
	}

	filecreate_1(&input, cl);

	filep = fopen(input.file_name, "rb");

	if( filep == NULL ) {
		printf("FP TA NULL\n");
    // caso filep == NULL, exit(3)? 
    // exit(3);
  }

	while (1) {
		fread(input.buffer, 4096, 1, filep);
	
		ret = filesend_1(&input, cl);
		result += *ret;

		if ( feof(filep) ){
			break;
		}
	}

	fclose(filep);


	printf("Enviados: %d bytes\n", result);

	clnt_destroy(cl);

	exit(0);

}
