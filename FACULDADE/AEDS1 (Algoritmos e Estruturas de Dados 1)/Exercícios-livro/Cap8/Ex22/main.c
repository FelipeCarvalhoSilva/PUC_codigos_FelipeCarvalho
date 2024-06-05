#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void preenche(float *matriz) {
  srand((unsigned)time(NULL));
  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 5; j++) {
      *(matriz + (i * 5) + j) = rand() % 51;
      printf("%.0f ", *(matriz + (i * 5) + j));
    }
    printf("\n");
  }
}
void abaixo6linha(float *matriz) {
  char *nome = (char *)malloc(15 * sizeof(char));
  scanf(" %[^\n]", nome);
  FILE *arq = fopen(nome, "w");
  float soma = 0;
  for (int i = 6; i < 10; i++) {
    for (int j = 0; j < 5; j++) {
      soma += *(matriz + (i * 5) + j);
    }
  }
  for (int i = 0; i < 10; i++) {
    for (int o = 0; o < 5; o++) {
      fprintf(arq, "%.0f ", *(matriz + (i * 5) + o));
    }
    fprintf(arq, "\n");
  }
  fprintf(arq, "%.0f soma", soma);
  printf("%.0f soma", soma);
}
int main(void) {
  float *matriz = (float *)malloc(10 * 5 * sizeof(float));
  preenche(matriz);
  abaixo6linha(matriz);
  return 0;
}