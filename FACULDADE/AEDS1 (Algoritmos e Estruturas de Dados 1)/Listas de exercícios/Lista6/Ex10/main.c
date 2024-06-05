#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
Escreva um programa em C para contar o número de vogais e consoantes em uma
cadeia de caracteres (vetor de char como string) usando um ponteiro.
*/
int main(void) {
  char *cadeia = (char *)malloc(50 * sizeof(char));
  int vogais = 0, consoantes = 0;
  scanf("%[^\n]", cadeia);
  for (int i = 0; i < 50; i++) {
    if (*(cadeia + i) == 'a' || *(cadeia + i) == 'e' || *(cadeia + i) == 'i' ||
        *(cadeia + i) == 'o' || *(cadeia + i) == 'u' || *(cadeia + i) == 'A' ||
        *(cadeia + i) == 'E' || *(cadeia + i) == 'I' || *(cadeia + i) == 'O' ||
        *(cadeia + i) == 'U')
      vogais++;
    else if (*(cadeia + i) != '\0' && *(cadeia + i) != ' ')
      consoantes++;
  }

  printf("Vogais: %d\n", vogais);
  printf("Consoantes: %d\n", consoantes);

  return 0;
}