#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>

int main(int argc, char *argv[]) {

  if (argc != 3){
    printf("utilisation : %s ip_serveur port_serveur\n", argv[0]);
    exit(0);
  }

  int ds = socket(PF_INET, SOCK_DGRAM, 0);

   if (ds == -1){
      printf("Client : pb creation socket\n");
      exit(1);
   }

  //printf("Client: creation de la socket : ok\n");
   
  struct sockaddr_in adrServ;
  adrServ.sin_addr.s_addr = inet_addr(argv[1]);
  adrServ.sin_family = AF_INET;
  adrServ.sin_port = htons((short)atoi(argv[2]));
      
  int lgAdr = sizeof(struct sockaddr_in);
  printf("Press ENTER to send signal ('q' to exit)\n");

  while(1){
    printf("> ");
    char message[100];
    long int msg = 1;
    fgets(message, sizeof(message), stdin); 			                     
    message[strlen(message)-1]  = '\0';
    
    if(!strcmp(message, "q")){
      // Signal d'arret du client
      printf("> Client finished\n"); 
      break;
    }

    int snd = sendto(ds,&msg, sizeof(msg), 0, (struct sockaddr *) &adrServ, lgAdr);
    
    if(snd <= 0){
      perror("Client : erreur à l'envoi :");
    }
    if(snd == 1){
      printf("Client : message envoyé \n");
    }
  }

  close (ds);
}
