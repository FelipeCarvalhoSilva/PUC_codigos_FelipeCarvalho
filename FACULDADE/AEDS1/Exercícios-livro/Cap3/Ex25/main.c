#include <stdio.h>

int main(void) {
  /*Objetivo-
  Faça um programa que receba o custo de um espetáculo teatral e o preço do convite desse espetáculo. Esse programa deverá calcular e mostrar a quantidade de convites que devem ser vendidos para que, pelo menos, o custo do espetáculo seja alcançado.
  Cap 3 Ex25
  Autor-Felipe
  Data-17/08/23
    */

  //variaveis
float custoEspetaculo,precoIngresso,quantidadeConvites;
  //recebe valores
  printf("Insira O custo do espetáculo e o custo do convite.\n");
  scanf(" %f %f",&custoEspetaculo,&precoIngresso);

  //calculos
  quantidadeConvites=custoEspetaculo/precoIngresso;

  //resultados
  printf("Para que o custo do espetáculo seja recuperado\nCom o preço de convites a: $%.2f\nÉ necessario vender: %.f Convites.",precoIngresso,quantidadeConvites);
  
  return 0;
}