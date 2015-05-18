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
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>

// MAIN
int main(){

	char pfad[256];
	char eingabeText[256];

	int running=1;

	getcwd(pfad,256);
	getlogin_r(eingabeText,256);

	printf("%s: MoinMoin %s\n",pfad,eingabeText);

	while(running){

		printf("%s: ",pfad);
		scanf("%s", eingabeText);

		if (strcmp(eingabeText,"quit")==0){

			printf("Das Programm wird beendet.\n"); 
			running=0;

		} else if (strcmp(eingabeText,"version")==0){

			printf("HAW-Shell Version 1.0 Autor: Dimitri-Meier\n");      

		} else if ('/'==eingabeText[0]){

			if (opendir(eingabeText)== 0){
				
				printf("Sorry nicht gefunden\n");

			}else {

				if ('/' == eingabeText[strlen(eingabeText)-1]){
				
					eingabeText[strlen(eingabeText)-1] = 0;
					strcpy(pfad,eingabeText);

				} else {
					
					strcpy(pfad,eingabeText);
				}
				chdir(pfad);
			}




		} else if (strcmp(eingabeText,"help")==0){
		
			printf("== HELP ==\n\nhelp        : Zeigt dir diese Informationen an\nquit        : Beendet das Programm\nversion     : Zeigt dir die aktuelle Version und die Autoren\n/[Pfadname] : Wechselt das Arbeitsverzeichnis nach /[Pfadname]\n");

		} else if (fork()!=0){

			if (eingabeText[(strlen(eingabeText)-1)]!='&')
			wait();

		} else {
			
			if (eingabeText[(strlen(eingabeText)-1)]=='&'){
			
				eingabeText[strlen(eingabeText)-1]=0;
				printf("%s: ",pfad);
			}

			execlp(eingabeText,"",NULL);
			exit(1);
		}
	}
	return 1;
}
