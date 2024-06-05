#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/*
Faça um procedimento que preencha uma matriz M 5 x 5. Faça uma função que receba
uma matriz preenchida, calcule e retorne cada uma das somas a seguir (uma função
para cada letra abaixo): (a) da linha 4 de M (b) da coluna 2 de M (c) da
diagonal principal (d) da diagonal secundária (e) de todos os elementos da
matriz. Faça um programa que faça as devidas declarações e acione os módulos
para exemplificar o seu uso.
*/
void preenche(int *matriz) {
  srand((unsigned)time(NULL));
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      scanf("%d", (matriz + (i * 5) + j));
    }
  }
}
// A
int somaLinha4(int *matriz) {
  int soma = 0;
  for (int i = 0; i < 5; i++) {
    soma += *(matriz + (i + 3 * 5));
  }
  return soma;
}

// B
int somaColuna2(int *matriz) {
  int soma = 0;
  for (int i = 0; i < 5; i++) {
    soma += *(matriz + (5 * i) + 1);
  }
  return soma;
}

// C
int somaDiagonalPrincipal(int *matriz) {
  int soma = 0;
  for (int i = 0; i < 5; i++) {
    soma += *(matriz + (5 * i) + i);
  }
  return soma;
}

// D
int somaDiagonalSecundaria(int *matriz) {
  int soma = 0;
  for (int i = 0; i < 5; i++) {
    soma += *(matriz + (4 * (i + 1)));
  }
  return soma;
}

// E
int somaTodosElementos(int *matriz) {
  int soma = 0;
  for (int linha = 0; linha < 5; linha++) {
    for (int col = 0; col < 5; col++) {
      soma += *(matriz + (5 * linha) + col);
    }
  }
  return soma;
}

// Main
int main(void) {
  int *matriz = (int *)malloc(5 * 5 * sizeof(int));
  preenche(matriz);
 
  somaLinha4(matriz);
  printf("%d\n", somaLinha4(matriz));
  printf("%d\n", somaColuna2(matriz));
  printf("%d\n", somaDiagonalPrincipal(matriz));
  printf("%d\n", somaDiagonalSecundaria(matriz));
  printf("%d\n", somaTodosElementos(matriz));
  return 0;
}