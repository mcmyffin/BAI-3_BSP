/**
 * hawsh.c
 * Author: Dimitri
 * Version: 1.0
 */

// HEADER
#include <strings.h>
#include <stdio.h>
#include <fcntl.h>
#include <string.h>
//#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

// MAIN
int main(){

  char pfad[256];
  bzero(pfad, sizeof(pfad));
  char str[256];
  bzero(str, sizeof(str));

  int k=1;
  int j=1;

  getcwd(pfad,256);
  getlogin_r(str,256);

  printf("%s: MoinMoin %s\n",pfad,str);

  while(k){

    printf("%s: ",pfad);
    scanf("%s", str);

    if (str[strlen(str)-1] == '&'){
        str[strlen(str)-1] = 0;
        if(fork()!=0) continue;
    }
    
    if (strcmp(str,"quit")==0){
      printf("Das Programm wird beendet.\n"); 
      k=0;
      j=0;
    }
    if (strcmp(str,"version")==0){
      printf("HAW-Shell Version 1.0 Autor: Dimitri-Meier\n");
      j=0;      
    }

    if ('/'==str[0]){
      if (opendir(str)== 0){
        printf("Sorry nicht gefunden\n");
      } else {
        if ('/' == str[strlen(str)-1]){
            str[strlen(str)-1] = 0;
            strcpy(pfad,str);
            setenv(pfad);
        } else {
            strcpy(pfad,str);
        }
          j=0;
      }
    }
    if (strcmp(str,"help")==0){
      printf("== HELP ==\n\nhelp        : Zeigt dir diese Informationen an\nquit        : Beendet das Programm\nversion     : Zeigt dir die aktuelle Version und die Autoren\n/[Pfadname] : Wechselt das Arbeitsverzeichnis nach /[Pfadname]\n");
      j=0;
    }

    if (j){     
      j=0;

      if (fork()!=0){
	    wait();
      } else {
	    system(str);
	    k=0;
      }
    }
    j=1;
  }
  return 1;
}
