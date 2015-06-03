#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>



int main () {

	printf("Geben Sie den Namen der neuen Datei an:\n");

    // ertelle ein String mit 30 Länge + (1 '\0' Terminierung)
	int inputlen = 31;    
	char input[inputlen];

    // Eingabeaufforderung
    fgets( input, inputlen, stdin);

    // entfernen von Leerzeichen und \n
    int i,j;
    char *output=input;
    for (i = 0, j = 0; i < strlen(input); i++,j++)
    {
        if (input[i]!=' ' && input[i] != '\n')
            output[j]=input[i];
        else
            j--;
    }
    output[j]=0;


    // ertelle File mit Mode 0700
    mode_t mode = S_IRWXU;
    int file = creat(output, mode);

    if(file){
        // schließe das erstellte File
        close(file);
        printf("Das File %s wurde erfolgreich erstellt\n",output);
    
    }else{
    	printf("Das File %s konnte nicht erstellt werden\n",output);
        return 1;
    }

    // program exit
	return 0;
}
