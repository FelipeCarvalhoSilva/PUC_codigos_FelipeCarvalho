#include <stdio.h>
#include <math.h>

int main(void) {
    /*
  Objetivo- Faça um programa que calcule e mostre a área de um círculo. Sabe-se que: Área = pi * R2
  Cap 3 Ex10
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  double raio, area;

  //recebe raio
  printf("Insira o raio de um circulo: \n");
  scanf(" %lf",&raio);

  //calculos
  area= 3.141*(pow(raio,2));

  //resultados
  printf("O raio da circunferencia de: %.2lf\n Tem a area de: %.3lf para PI=3.141\n",raio,area);
  
  
  return 0;
}