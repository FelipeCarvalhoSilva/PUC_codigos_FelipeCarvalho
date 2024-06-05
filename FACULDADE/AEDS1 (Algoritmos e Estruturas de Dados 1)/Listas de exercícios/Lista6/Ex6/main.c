#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/*
Faça um programa para preencher uma matriz 4 x 4, em seguida apresentar na tela
a soma dos elementos abaixo da diagonal principal. Mostre na tela os elementos
da diagonal principal também.
*/
void preenche(int *matriz) {
  srand((unsigned)time(NULL));
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 4; j++) {
      scanf("%d  ",(matriz+(4*i)+j));
      printf("%d ",*(matriz+(i*4)+j));
    }
    printf("\n");
  }
}
int diagonalPrincipal(int *matriz){
  int soma=0;
  for(int i=0;i<4;i++){
    soma+=*(matriz+(i*4)+i);
    printf("%d ",*(matriz+(i*4)+i));
  }
  return soma;
}
int abaixoDiagonalPrincipal(int *matriz){
  int soma=0;
  for(int i=1;i<4;i++){
    soma+=*(matriz+(i*4)+i-1);
  }
  return soma;
}
int main(void) {
  int *matriz = (int *)malloc(4 * 4 * sizeof(int));
  preenche(matriz);
  printf("%d\n",abaixoDiagonalPrincipal(matriz));
  diagonalPrincipal(matriz);
  return 0;
}