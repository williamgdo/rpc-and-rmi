#include <rpc/rpc.h>
#include <stdio.h>
#include <stdlib.h>
#include "file.h"


int *
fileproc_1_svc(int * in, struct svc_req * rqstp)
{
	static int out;

	out = *in * *in;

	return (&out);
}
