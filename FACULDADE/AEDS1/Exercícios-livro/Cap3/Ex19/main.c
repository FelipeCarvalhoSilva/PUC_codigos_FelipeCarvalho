#include <stdio.h>

int main(void) {
   /*Objetivo-
  Cada degrau de uma escada tem X de altura. Faça um programa que receba essa altura e a altura que o usuário deseja alcançar subindo a escada, calcule e mostre quantos degraus ele deverá subir para atingir seu objetivo, sem se preocupar com a altura do usuário. Todas as medidas fornecidas devem estar em metros.
  Cap 3 Ex19
  Autor-Felipe
  Data-17/08/23
    */

  //variaveis
  float alturaDegrau,alturaDesejada,quantidadeDegrau;

  //recebe variaveis
  printf("Coloque a altura do degrau e a altura que deseja chegar na subida (tudo em METROS)\n");
  
  scanf(" %f %f", &alturaDegrau,&alturaDesejada);

  //calculos
  quantidadeDegrau=alturaDesejada/alturaDegrau;

  //resultados
  printf("A escada com degraus de: %.2fMetros\nPrecisa de: %.1f degrais subidos\nPara alcançar: %.2fMetros\n",alturaDegrau,quantidadeDegrau,alturaDesejada);
  return 0;
}