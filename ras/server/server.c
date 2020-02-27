#include "server.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

void server_init(server *serv, int port)
{
  int sockfd = socket(AF_INET, SOCK_DGRAM, 0);
  if(sockfd < 0)
    {
      printf("Error creating socket fd.\n");
      exit(EXIT_FAILURE);
    }

  struct sockaddr_in servaddr;
  
  memset(&servaddr, 0, sizeof(servaddr));

  servaddr.sin_family      = AF_INET; // IPv4 
  servaddr.sin_addr.s_addr = INADDR_ANY; 
  servaddr.sin_port        = htons(port);

  if (bind(sockfd, (const struct sockaddr *)&servaddr, sizeof(servaddr)) < 0) 
    { 
      printf("Error binding socket.\n");
      close(sockfd);
      exit(EXIT_FAILURE);
    }

  serv->sockfd = sockfd;
}

void server_get_msg(server *serv)
{
  int n = recvfrom(serv->sockfd, serv->buffer,
		   sizeof(serv->buffer), MSG_WAITALL,
		   NULL, NULL);
  if(n <= 0)
   {
     printf("Error getting msg.\n");
     close(serv->sockfd);
     exit(EXIT_FAILURE);
   }
}

void server_print_msg(server *serv)
{
  printf("Msg data = %i\n",serv->buffer[0]);
}
