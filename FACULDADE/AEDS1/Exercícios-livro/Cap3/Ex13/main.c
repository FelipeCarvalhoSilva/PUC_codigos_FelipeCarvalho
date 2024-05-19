#include <stdio.h>

int main(void) {
    /*
  Objetivo-
Sabe-se que:
pé = 12 polegadas
1 jarda = 3 pés
1 milha = 1,760 jarda
Faça um programa que receba uma medida em pés, faça as conversões a seguir e mostre os resultados.
a) polegadas;
b) jardas;
c) milhas.
  Cap 3 Ex13
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  double pes,jarda,milha,polegadas;

  //recebe valores em pés
  printf(" Insira um valor numerico em Pés: \n");
  scanf(" %lf",&pes);

  //calculos
  polegadas=pes*12;
  jarda=pes/3;
  milha=jarda/1760;

  //mostra resultados
  printf("Para um valor de: %.2f pés\nPolegadas=%.2f\nJardas=%.2f\nMilhas=%2f",pes,polegadas,jarda,milha);
  
  return 0;
}