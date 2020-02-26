#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h> 
#include <unistd.h>

int main(int argc, char **argv)
{
  if(argc < 4)
    {
      printf("Missing server IP, port or msg value.\n");
      exit(EXIT_FAILURE);
    }
  
  int sockfd;
  if((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0)
    {
      printf("Socket creation failed.\n");
      exit(EXIT_FAILURE);
    }

  struct sockaddr_in servaddr;
  memset(&servaddr, 0, sizeof(servaddr));
  
  servaddr.sin_family = AF_INET;
  servaddr.sin_port = htons(atoi(argv[2]));
  servaddr.sin_addr.s_addr = inet_addr(argv[1]);

  char buffer[1];
  buffer[0] = atoi(argv[3]);
  
  int n = sendto(sockfd, (const char *)buffer, sizeof(buffer),
		 MSG_CONFIRM, (const struct sockaddr *) &servaddr,  
		 sizeof(servaddr));
  if(n < 0)
    {
      printf("Error sending message.\n");
      close(sockfd);
      exit(EXIT_FAILURE);
    }
  
  close(sockfd);
  return 0;
}
