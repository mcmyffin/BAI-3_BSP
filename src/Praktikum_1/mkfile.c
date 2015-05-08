#include <stdio.h>
#include <stdlib.h>

int main () {

	printf("Geben Sie den Namen der neuen Datei an:\n");

    // ertelle ein String mit 30 Länge + (1 '\0' Terminierung)
    char input[31];

    // Eingabeaufforderung
    fgets( input, 31, stdin);

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
    int file = creat(output, 0700);

    // schließe das erstellte File
    close();

    // true == (int i: i > 0 oder i < 0)
    // false == (int i: i == 0)

    if(file){
        printf("Das File %s wurde erfolgreich erstellt\n",output);
    }
	else{
	    printf("Das File %s konnte nicht erstellt werden\n",output);
	}

    // program exit
	return 0;
}
