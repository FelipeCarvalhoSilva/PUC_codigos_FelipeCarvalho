#include <stdio.h>
#include <math.h>

int main(void) {
  /*
  Objetivo- Faça um programa que receba um número positivo e maior que zero, calcule e mostre:
  a) o número digitado ao quadrado;
 b) o número digitado ao cubo;
 c) a raiz quadrada do número digitado;
 d) a raiz cúbica do número digitado.
  Cap 3 Ex11
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  float numero,cubo,quadrado,raizquadrada,raizcubica;

  //recebe valores
  printf("coloque um NUMERO POSITIVO e MAIOR QUE 0:  \n");
  scanf(" %f",&numero);

  //calculos
  quadrado=pow(numero,2);
  cubo=pow(numero,3);
  raizquadrada=sqrt(numero);
  raizcubica=cbrt(numero);

  //resultados
  printf("Para o Numero: %.2f\nelevado a 2: %.2f\nelevado a 3: %.2f\nraizQuadrada: %.2f\nraizCubica: %.2f\n",numero,quadrado,cubo,raizquadrada,raizcubica);
  
  
  
  return 0;
}