#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool metodo(char *palavra) {
  for (int i = 0; i < strlen(palavra); i++) {
    if (palavra[i] != palavra[strlen(palavra) - 1 - i])
      return (false);
  }
  return (true);
}
int main(void) {
  char *palavra = (char *)malloc(1000 * sizeof(char));

  while (strcmp(palavra, "FIM") != 0) {
    scanf(" %[^\n]", palavra);
    if (strcmp(palavra, "FIM") != 0) {
      if (metodo(palavra) == true)
        printf("SIM\n");
      else
        printf("NAO\n");
    }
  }
  free(palavra);
  return 0;
}