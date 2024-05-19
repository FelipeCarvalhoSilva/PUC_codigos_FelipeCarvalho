#include <stdio.h>

int main(void) {
  /*
  Objetivo- Faça um programa que calcule e mostre a área de um triângulo. Sabe-se que: Área = (base * altura)/2.
  Cap 3 Ex9
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  float base,altura,area;

  //recebe base e altura
  printf("Insira respectivamente a Base e Altura.\n");
  scanf(" %f %f",&base,&altura);

  //calculos
  area=(base*altura)/2;

  //resultados
  printf("Com base de: %.2f e altura de: %.2f\nA area é de: %.2f",base,altura,area);
  return 0;
}