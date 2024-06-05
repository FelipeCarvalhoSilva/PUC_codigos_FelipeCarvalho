#include <stdio.h>
#include <stdlib.h>
/*
Escrever um procedimento que preenche uma matriz M(10,10) e a escreve. Faça
outros proce- dimentos que recebam uma matriz preenchida, realize as trocas
indicadas a seguir (um procedi- mento para cada uma delas) e exiba a matriz
resultante da troca: (a) a linha 2 com a linha 8 (b) a coluna 4 com a coluna 10
(c) a diagonal principal com a diagonal secundária
(d) a linha 5 com a coluna 10.
Faça um programa que faça as devidas declarações e acione os módulos para
exemplificar o seu uso.*/
void preencheMatriz(int *matriz) {
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      scanf("%d", (matriz + (ln * 10) + col));
    }
  }
}
void troca(int *matriz) {
  int aux;
  // linha 2 com 8
  for (int i = 0; i < 10; i++) {
    aux = *(matriz + (2 * 10) + i);
    *(matriz + (2 * 10) + i) = *(matriz + (8 * 10) + i);
    *(matriz + (8 * 10) + i) = aux;
  }
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      printf("%d ", *(matriz + (ln * 10) + col));
    }
  }

  // coluna 4 com 10
  for (int o = 0; o < 10; o++) {
    aux = *(matriz + (o * 10) + 4);
    *(matriz + (o * 10) + 4) = *(matriz + (o * 10) + 9);
    *(matriz + (o * 10) + 9) = aux;
  }
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      printf("%d ", *(matriz + (ln * 10) + col));
    }
  }

  // diagonal principal com secundaria
  for (int i = 0; i < 10; i++) {
    aux = *(matriz + (10 * i) + 9 - i);
    *(matriz + (10 * i) + 9 - i) = *(matriz + (10 * i) + i);
    *(matriz + (10 * i) + i) = aux;
  }
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      printf("%d ", *(matriz + (ln * 10) + col));
    }
  }

  // linha 5 com coluna 10
  for (int i = 0; i < 10; i++) {
    aux = *(matriz + (10 * i) + 9);
    *(matriz + (10 * i) + 9) = *(matriz + (5 * 10) + i);
    *(matriz + (5 * 10) + i) = aux;
  }
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      printf("%d ", *(matriz + (ln * 10) + col));
    }
  }
}
int main(void) {
  int *matriz = (int *)malloc(10 * 10 * sizeof(int));
  preencheMatriz(matriz);
  for (int ln = 0; ln < 10; ln++) {
    for (int col = 0; col < 10; col++) {
      printf("%d ", *(matriz + (ln * 10) + col));
    }
  }
  troca(matriz);

  return 0;
}