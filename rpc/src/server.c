#include <rpc/rpc.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "file.h"


int* filecreate_1_svc(file_in * in, struct svc_req * rqstp)
{
	static int out = 0;

	FILE * fp;
	char  name[100] = {0};

	//name = (char *) malloc(sizeof(char) * (5 + strlen(in->file_name)));

	strcat(name, "new_");
	strcat(name, in->file_name);

	fp = fopen(name, "w");

	fclose(fp);

	return (&out);
}

int *
filesend_1_svc(file_in * in, struct svc_req * rqstp)
{

	static int out = 0;

	FILE * fp;
	char name[100] = {0};

	//name = (char *) malloc(sizeof(char) * (5 + strlen(in->file_name)));

	strcat(name, "new_");
	strcat(name, in->file_name);


	fp = fopen(name, "ab");

	out += fwrite(in->buffer, 1, sizeof(in->buffer), fp);

	fclose(fp);

	return (&out);
}
