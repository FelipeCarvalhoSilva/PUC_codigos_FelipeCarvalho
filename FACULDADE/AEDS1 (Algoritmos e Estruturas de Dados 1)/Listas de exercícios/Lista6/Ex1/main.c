#include <stdio.h>
/*
 Faça um procedimento que receba e preencha um vetor com as notas de uma turma
de 10 alunos. Faça um outro procedimento que receba um vetor preenchido com as
notas, calcule a média da turma e conte quantos alunos obtiveram nota acima da
média. Esse procedimento deve exibir a média e o resultado da contagem. Faça um
programa que declare as devidas variáveis e acione os procedimentos.
*/
void procedimento(float notas[0]) {
  for (int i = 0; i < 10; i++) {
    scanf("%f", &notas[i]);
  }
}
void calculaMedia(float notas[0], float media) {
  int acimaMedia = 0;
  for (int i = 0; i < 10; i++) {
    media += notas[i];
  }
  for (int i = 0; i < 10; i++) {
    if (notas[i] > (media / 10))
      acimaMedia++;
  }
  printf("Média: %.2f\n", media / 10);
  printf("Alunos acima da média: %d", acimaMedia);
}
int main(void) {
  float notas[10];
  float media = 0;
  procedimento(&notas[0]);
  calculaMedia(&notas[0], media);
  return 0;
}