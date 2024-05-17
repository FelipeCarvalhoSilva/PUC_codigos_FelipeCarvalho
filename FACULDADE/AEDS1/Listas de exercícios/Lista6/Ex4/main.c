#include <stdio.h>
#include <stdlib.h>
/*
Escreva um programa em C para encontrar o maior elemento em um vetor de inteiros
usando a Alocação de Memória Dinâmica. Peça para o usuário inserir inicialmente
o tamanho do vetor a ser criado, e após, peça para ele inserir um a um todos os
valores do vetor
*/
int main(void) {
  int *vetor;
  int n, maior = -999;
  scanf("%d", &n);
  vetor = (int *)malloc(n * sizeof(int));
  for (int i = 0; i < n; i++) {
    scanf("%d", (vetor + i));
    if (*(vetor + i) > maior)
      maior = *(vetor + i);
  }
  printf("%d", maior);
  return 0;
}