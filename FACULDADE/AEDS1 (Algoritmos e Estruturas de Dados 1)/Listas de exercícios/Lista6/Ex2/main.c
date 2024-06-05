#include <stdio.h>
/*
Em uma cidade, sabe-se hipoteticamente que, em janeiro de 2021, não ocorreu
temperatura inferior a 15°C, nem superior a 40°C. Faça um programa que armazene
as temperaturas de cada dia de janeiro em um vetor (de 31 posições), calcule e
imprima: • A menor e a maior temperatura ocorrida; • A temperatura média; • O
número de dias nos quais a temperatura foi inferior a temperatura média
*/
int main(void) {
  int temperaturas[31];
  int menor = 9999, maior = -9999, abaixoMedia = 0;
  float media = 0;
  // preenche temperaturas no vetor
  for (int i = 0; i < 31; i++) {
    scanf("%d", &temperaturas[i]);
    media += temperaturas[i];
  }
  media /= 31;

  // checa condições dadas
  for (int i = 0; i < 31; i++) {
    if (temperaturas[i] > maior)
      maior = temperaturas[i];
    else if (temperaturas[i] < menor)
      menor = temperaturas[i];
    if (temperaturas[i] < media)
      abaixoMedia++;
  }
  printf("Menor e maior temperatura: %d e %d\n", menor, maior);
  printf("Média de temperatura: %.2f\n", media);
  printf("Número de dias nos quais a temperatura foi inferior à temperatura média: %d",
         abaixoMedia);

  return 0;
}