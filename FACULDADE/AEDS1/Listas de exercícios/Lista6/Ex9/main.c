#include <stdio.h>
#include <stdlib.h>
/*
Escreva um programa em C para ordenar um vetor de inteiros usando ponteiro. A
primeira entrada deve ser o tamanho do vetor a ser inserido.*/
int main(void) {
  int n;
  scanf("%d", &n);
  int *vetor = (int *)malloc(n * sizeof(int));
  for (int i = 0; i < n; i++) {
    scanf("%d", (vetor + i));
  }
  for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
      if (*(vetor + i) > *(vetor + j)) {
        int temp = *(vetor + i);
        *(vetor + i) = *(vetor + j);
        *(vetor + j) = temp;
      }
    }
  }
  for (int a = 0; a < n; a++) {
    printf("%d ", *(vetor + a));
  }

  return 0;
}

