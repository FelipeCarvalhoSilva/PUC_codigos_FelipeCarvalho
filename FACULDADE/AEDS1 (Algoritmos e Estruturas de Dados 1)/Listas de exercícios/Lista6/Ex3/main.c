#include <stdio.h>
/*
Faça um procedimento que preencha 2 vetores X e Y com 10 elementos cada um
(ocupando as posições de 0 a 9 em cada vetor). Faça um outro procedimento que
receba dois vetores preenchidos e gera um novo vetor com os elementos desses 2
vetores intercalados de tal forma que nas posições ímpares do novo vetor estejam
os elementos do primeiro vetor e nas posições pares os elementos do segundo
vetor recebido por parâmetro. Faça um procedimento que receba e exiba o conteúdo
de um vetor. Faça um programa que faça as devidas declarações e acione os
módulos para exemplificar o seu uso.
*/
void preenche(int x[10], int y[10]) {
  for (int i = 0; i < 10; i++) {
    scanf("%d", &x[i]);
  }
  for (int i = 0; i < 10; i++) {
    scanf("%d", &y[i]);
  }
}
void contatena(int x[10], int y[10]) {
  int novo[20];
  for (int i = 0; i < 10; i++) {
    novo[2 * i] = x[i];
    novo[2 * i + 1] = y[i];
  }
  for (int i = 0; i < 20; i++) {
    printf("%d ", novo[i]);
  }
}
int main(void) {
  int x[10], y[10];
  preenche(&x[0], &y[0]);
  contatena(&x[0], &y[0]);
  return 0;
}