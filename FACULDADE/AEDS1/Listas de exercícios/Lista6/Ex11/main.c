#include <stdio.h>
#include <stdlib.h>
/*
Escreva um programa em C para calcular a soma de todos os elementos em um vetor
de inteiros usando ponteiros. A primeira entrada deve ser o tamanho do vetor a
ser inserido.
*/
int main(void) {
  int n, soma = 0;
  scanf("%d", &n);
  int *vetor = (int *)malloc(n * sizeof(int));
  for (int i = 0; i < n; i++) {
    scanf("%d", (vetor + i));
  }
  for (int i = 0; i < n; i++) {
    soma += *(vetor + i);
  }
  printf("%d\n",soma);
  return 0;
}