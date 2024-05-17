#include <stdio.h>
/*
Faça um procedimento que recebe a média final de um aluno, identifica e exibe o
seu conceito, conforme a tabela abaixo. Faça um programa que leia a média de N
alunos, acionando o proce- dimento para cada um deles. (N deve ser lido do
teclado)
*/

void procedimento(float media){
  if(media>=90)printf("A\n");
  else if(media<=89&&media>=80)printf("B\n");
  else if(media<=79&&media>=70)printf("C\n");
  else if(media<=69&&media>=60)printf("D\n");
  else if(media<=59&&media>=40)printf("E\n");
  else if(media<=39)printf("F\n");
  else printf("");
}

int main(void) {
  float media;
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%f", &media);
    procedimento(media);
  }
  return 0;
}