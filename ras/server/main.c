#include "server.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
  if(argc < 2)
    {
      printf("PORT number missing.\n");
      exit(EXIT_FAILURE);
    }

  server serv;

  server_init(&serv, atoi(argv[1]));

  printf("Server listening on PORT %s\n", argv[1]);

  for(;;)
    {
      printf("Waiting for msg...\n");
      server_get_msg(&serv);
      printf("New msg.\n");
      server_print_msg(&serv);
    }
  
  return 0;
}
