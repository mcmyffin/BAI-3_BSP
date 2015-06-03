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

	int  maxLen = 256;
	char pfad[maxLen];
	char eingabeText[maxLen];

	int running = 1;

	// Speichert das aktuelle Verzeichnis in die Variable
	getcwd(pfad,maxLen);

	// Speichert den Usernamen in die Variable
	getlogin_r(eingabeText,maxLen);

	// Gibt den Begruessungstext aus
	printf("%s: MoinMoin %s\n",pfad,eingabeText);

	while(running){

		// Gibt das aktuelle Verzeichnis aus
		printf("%s: ",pfad);

		scanf("%255s", eingabeText);

		// Wenn quit , dann beenden
		if (strcmp(eingabeText,"quit")==0){

			printf("Das Programm wird beendet.\n"); 
			running=0;

		// Wenn version, dann zeige Version
		} else if (strcmp(eingabeText,"version")==0){

			printf("HAW-Shell Version 1.0 Autor: Dimitri-Meier\n");      

		// Wenn erstes Char '/', dann oeffne Verzeichnis
		} else if ('/'==eingabeText[0]){

			// Wenn Pfad ungueltig, dann informiere User
			if (chdir(eingabeText)== -1){
				
				printf("Sorry nicht gefunden\n");

			}else {
				
				// Wenn im Pfad am ende ein '/' vorkommt, dann entferne diesen
				if ('/' == eingabeText[strlen(eingabeText)-1]){
				
					eingabeText[strlen(eingabeText)-1] = 0;
					strcpy(pfad,eingabeText);

				} else {
					
					strcpy(pfad,eingabeText);
				}
				// Pfad wechseln
				chdir(pfad);
			}

		// Wenn help, dann zeige die Bedienungsanleitung
		} else if (strcmp(eingabeText,"help")==0){
		
			printf("== HELP ==\n\nhelp        : Zeigt dir diese Informationen an\nquit        : Beendet das Programm\nversion     : Zeigt dir die aktuelle Version und die Autoren\n/[Pfadname] : Wechselt das Arbeitsverzeichnis nach /[Pfadname]\n");

		// Wenn kein Kindprozess
		} else if (fork()!=0){

			// Wenn in der Eingabe kein '&' vorkommt, dann warte.
			if (eingabeText[(strlen(eingabeText)-1)]!='&')
				wait();

		// Kindprozess
		} else {
			// Wenn in der Eingabe ein '&' vorkommt
			if (eingabeText[(strlen(eingabeText)-1)]=='&'){
			
				// eleminiere das '&'
				eingabeText[strlen(eingabeText)-1]=0;
				// zeige das aktuelle Verzeichnis
				printf("%s: ",pfad);
			}
			
			execlp(eingabeText,"",NULL);
			printf("ungueltige Eingabe\n");
			exit(0);
		}
	}
	return 0;
}
