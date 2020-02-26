#ifndef SERVER_H
#define SERVER_H

typedef struct server{
  int  sockfd;
  char buffer[1];
}server;

void server_init(server *serv, int port);
void server_get_msg(server *serv);
void server_print_msg(server *serv);

#endif
