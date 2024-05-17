#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/*
Faça um procedimento que preencha 2 matrizes, A 4 x 6 e B 4 x 6. Faça uma função
para cada uma das situações a seguir, que recebe duas matrizes preenchidas,
calcula e retorna as matrizes indicadas : (a) uma matriz S que seja a soma de A
e B. (b) uma matriz D que seja a diferença de A e B. (A - B). Faça um programa
que faça as devidas declarações e acione os módulos para exemplificar o seu uso.
Escreva as matrizes resultantes do acionamento de cada uma das funções
*/
void preenche(int *matriz) {
  srand((unsigned)time(NULL));
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 6; j++) {
      scanf("%d", (matriz + (i * 6) + j));
    }
  }
}

void somaMatrizes(int *matrizA, int *matrizB) {
  // Soma do elemento da matriz A com B
  int *matrizS = (int *)malloc(4 * 6 * sizeof(int));

  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 6; j++) {
      *(matrizS + (i * 6) + j) =
          *(matrizA + (i * 6) + j) + *(matrizB + (i * 6) + j);
      printf("%d ", *(matrizS + (i * 6) + j));
    }
  }
  free(matrizS);
}

void subtraiMatrizes(int *matrizA, int *matrizB) {
  // Subtração do elemento da matriz A com B
  int *matrizD = (int *)malloc(4 * 6 * sizeof(int));
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 6; j++) {
      *(matrizD + (i * 6) + j) =
          *(matrizA + (i * 6) + j) - *(matrizB + (i * 6) + j);
      printf("%d ", *(matrizD + (i * 6) + j));
    }
  }
  free(matrizD);
}

int main(void) {
  int *matrizA = (int *)malloc(4 * 6 * sizeof(int));
  int *matrizB = (int *)malloc(4 * 6 * sizeof(int));
  preenche(matrizA);
  preenche(matrizB);
  somaMatrizes(matrizA, matrizB);
  subtraiMatrizes(matrizA, matrizB);
  return 0;
}