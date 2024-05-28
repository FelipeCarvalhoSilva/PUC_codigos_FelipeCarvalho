#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int metodo(char *palavra, int i) {
  if (i == strlen(palavra))
    return 0;
  else {
    if (palavra[i] != palavra[strlen(palavra) - 1 - i])
      return metodo(palavra, i + 1) + 1;
  }
  return metodo(palavra, i + 1) + 0;
}
int main(void) {
  char *palavra = (char *)malloc(1000 * sizeof(char));

  while (strcmp(palavra, "FIM") != 0) {
    scanf(" %[^\n]%*c", palavra);
    if (strcmp(palavra, "FIM") != 0) {
      if (metodo(palavra, 0) <= 0)
        printf("SIM\n");
      else
        printf("NAO\n");
    }
  }
  free(palavra);
  return 0;
}