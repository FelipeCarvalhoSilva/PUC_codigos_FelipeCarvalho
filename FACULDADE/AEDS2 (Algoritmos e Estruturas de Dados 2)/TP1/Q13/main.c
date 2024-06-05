#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *altera(char *input, int n, char c1, char c2) {

  if (n == strlen(input)) {
    char *modificado = (char *)malloc(n * sizeof(char));
    strcpy(modificado, input);
    return modificado;
  }
  if (input[n] == c1) {
    input[n] = c2;
    return altera(input, n + 1, c1, c2);
  } else {

    return altera(input, n + 1, c1, c2);
  }
}

int main(void) {

  srand(4);

  char *input = (char *)malloc(500 * sizeof(char));
  while (strcmp(input, "FIM") != 0) {
    char c1 = 'a' + (rand() % 26);
    char c2 = 'a' + (rand() % 26);
    scanf(" %[^\n]", input);
    if (strcmp(input, "FIM") != 0) {
      printf("%s\n", altera(input, 0, c1, c2));
    }
  }
free(input);
  return 0;
}