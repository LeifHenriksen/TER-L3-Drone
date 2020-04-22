########################################
#~ définitions
########################################

# nom de l'executable
#BIN=runPeriod

BIN=client  serveur
#BIN=bin/serveur

# liste des fichiers sources 
SRCS0=client.c
SRCS1=serveur.c

default: $(BIN)

########################################
#~ regles pour l'executable
########################################

%.o: %.c
	gcc -g -Wall -Iinclude -c $< -o $@

client: $(SRCS0:%.c=%.o)
	gcc -lpthread -o $@ $+

serveur: $(SRCS1:%.c=%.o)
	gcc -lpthread -o $@ $+

clean:
	rm -f $(BIN) *.o *~
