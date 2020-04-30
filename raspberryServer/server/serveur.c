#include <stdio.h>
#include <sys/types.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


char *reverse(char *str){
    char tmp, *src, *dst;
    size_t len;
    if (str != NULL)
    {
        len = strlen (str);
        if (len > 1) {
            src = str;
            dst = src + len - 1;
            while (src < dst) {
                tmp = *src;
                *src++ = *dst;
                *dst-- = tmp;
            }
        }
    }
    return str;
}

void itoa(int n, char s[]){
    int i, sign;

    if ((sign = n) < 0)        /* record sign */
        n = -n;                /* make n positive */
    i = 0;

    do {                       /* generate digits in reverse order */
        s[i++] = n % 10 + '0'; /* get next digit */
    } while ((n /= 10) > 0);   /* delete it */

    if (sign < 0)
        s[i++] = '-';

    reverse(s);
    s[i] = '\0';
    return;
}

int main(int argc, char *argv[]){
  
  if (argc<4 || atoi(argv[2]) < 0 || atoi(argv[2]) > 180){
    printf("utilisation: %s numero_port ouverture_trappe duree_ouverture\n\n      numero_port : il est recommande d'utiliser un port non assigne\n      ouverture_trappe : l'ouverture en degres de la trappe de relachement des graines (de 0 a 180) \n      duree_ouverture : durée pendant laquelle la trappe de relachement des graines reste ouverte (en seconde)\n\n", argv[0]);
    exit(1);
  }
 
  int ds = socket(PF_INET, SOCK_DGRAM, 0);
   
  if (ds == -1){
    perror("Serveur : probleme creation socket");
    exit(1); 
  }

  struct sockaddr_in serverAddr;
  serverAddr.sin_family = AF_INET;
  serverAddr.sin_addr.s_addr = INADDR_ANY;
  serverAddr.sin_port = htons((short)atoi(argv[1]));
  
  if(bind(ds, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0){
    perror("Serveur : erreur bind");
    close(ds); 
    exit(1); 
  }
 
  unsigned int ouverture = atoi(argv[2]);
  unsigned int temps = atoi(argv[3]);
  long int messageRecu;
    
  time_t rawtime;
  struct tm * timeinfo;

  struct sockaddr_in addrC ;
  socklen_t lgAddrC = sizeof(struct sockaddr_in) ;

  printf("ouverture : %u & temps : %u\n", ouverture, temps);

  while(1){
    
    int rcv = recvfrom(ds, &messageRecu, sizeof(messageRecu),0, (struct sockaddr *) &addrC, &lgAddrC);
    time(&rawtime);
    timeinfo = localtime(&rawtime);

    if(rcv < 0){
      perror("Serveur : erreur à la reception :");
      break;
    } else {
      if(messageRecu==1){
        printf("# SIG : %02d:%02d:%02d \n", timeinfo->tm_hour, timeinfo->tm_min, timeinfo->tm_sec); 
        // Envoie message au RAS

        char requete[200] = "python script.py ";
        char deg[10] = {0};
        char time[10] = {0};
        itoa(ouverture,deg);
        itoa(temps, time);
        strcat(requete, deg);
        strcat(requete," ");
        strcat(requete, time);

        system(requete);	
      }
    }
  }
  close (ds);
}
