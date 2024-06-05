#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
. Escreva um programa em C para imprimir todas as permutações de um vetor de
char usando ponteiros.*/

void swap(char *a, char *b) {
  char temp = *a;
  *a = *b;
  *b = temp;
}

void troca(char *vetor, int tamanho, int l) {
  if (l == tamanho - 1) {
    printf("%s ", vetor); // Imprime a permutação
  } else {
    for (int o = l; o < tamanho; o++) {
      swap((vetor+l), (vetor+o));
      troca(vetor, tamanho, l + 1);
      swap((vetor+l), (vetor+o)); // Desfaz a troca
    }
  }
}

int main(void) {
  char *vetor = (char *)malloc(20 * sizeof(char));
  scanf("%[^\n]", vetor);
  int tamanho = strlen(vetor);
  int l = 0;
  printf("As permutações da string são:\n");
  troca(vetor, tamanho, l);

  return 0;
}