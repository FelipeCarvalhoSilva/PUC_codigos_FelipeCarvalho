#include <stdio.h>

int main(void) {
  /*Objetivo-
  Faça um programa que receba uma hora formada por hora e minutos (um número real), calcule e
mostre a hora digitada apenas em minutos. Lembre-se de que:
■■ para quatro e meia, deve-se digitar 4.30;
■■ os minutos vão de 0 a 59.
  Cap 3 Ex24
  Autor-Felipe
  Data-17/08/23
    */

  //variaveis
  float hora,minutos;

  //recebe valores
  printf("Insira primeiro hora e depois minutos.\n");
  scanf(" %f %f",&hora,&minutos);

  //calculos
  hora*=60;

  //resultados
  printf(" As horas inseridas em minutos, são: %.fmin",(hora+minutos));
  return 0;
}