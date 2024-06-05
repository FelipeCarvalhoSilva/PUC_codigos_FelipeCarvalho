#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h> 

void insere(char *charVet, int tam, char input) {
    for (int i = tam - 1; i >= 0; i--) {
        charVet[i + 1] = charVet[i];
    }
    charVet[0] = input;
}

int main() {
    char input[100000];
    char *charVet; 
    int tam, subTam, index, index2;

    while (fgets(input, sizeof(input), stdin) != NULL) {
        tam = strlen(input);
        int n = 0;
        bool stopAssigning = false;


        charVet = (char *)malloc(tam * sizeof(char));

        index = strchr(input, '[') - input;
        index2 = strchr(input, ']') - input;

        for (int i = 0; i < tam; i++) {
            if (input[i] == '[') {
                stopAssigning = true;
            } else if (input[i] == ']') {
                stopAssigning = false;
            }
            if (stopAssigning) n++;

            if (!stopAssigning) {
                charVet[i - n] = input[i];
            }
        }

        if (index != -1 && index2 != -1) {
            strncpy(input, input + index + 1, index2 - index - 1);
            input[index2 - index - 1] = '\0';
        } else {
            strncpy(input, input + index + 1, tam - index - 2);
            input[tam - index - 2] = '\0';
        }

        subTam = strlen(input);
        for (int i = subTam - 1; i >= 0; i--) {
            insere(charVet, tam, input[i]);
        }

      for (int i = 0; i < tam; i++) {
        if (charVet[i] != ']' && charVet[i] != '[' && charVet[i] != '\0') {
            printf("%c", charVet[i]);
        }
      }

        free(charVet);
    }
    return 0;
}