#include <stdio.h>

int main(void) {
  /*
  Objetivo- Faça um programa que receba o valor de um depósito e o valor da taxa de juros, calcule e mostre o valor do rendimento e o valor total depois do rendimento.
  Cap 3 Ex8
  Autor-Felipe
  Data-16/08/23
    */
  
  //variaveis
  float deposito,juros,rendimento;

  //recebe valor do deposito e taxa de juros
  printf("Insira o valor do deposito e dos juros.\n");
  scanf(" %f %f",&deposito,&juros);

  //calculos
  rendimento=(deposito/100)*juros;
  deposito= deposito+rendimento;

  //resultados
  printf("Com o juros de: %.2fPorcento \nO rendimento é de: %.2f \n",juros,rendimento);
  printf("Apos o rendimento o valor total é: %.2f",deposito);

  return 0;
}