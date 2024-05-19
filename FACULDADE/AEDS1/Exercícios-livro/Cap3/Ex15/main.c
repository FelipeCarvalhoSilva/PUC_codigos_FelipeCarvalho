#include <stdio.h>

int main(void) {
   /*
  Objetivo-
  O custo ao consumidor de um carro novo é a soma do preço de fábrica com o percentual de lucro do distribuidor e dos impostos aplicados ao preço de fábrica. Faça um programa que receba o preço de fábrica de um veículo, o percentual de lucro do distribuidor e o percentual de impostos, calcule e mostre:

a) o valor correspondente ao lucro do distribuidor;
b) o valor correspondente aos impostos;
c) o preço final do veículo.
  Cap 3 Ex15
  Autor-Felipe
  Data-16/08/23
    */
//variaveis
  float precoFabrica,percentualLucro,impostos,custoConsumidor;

  //recebe variaveis
  printf("Insira o preço de fábrica de um veículo, o percentual de lucro do distribuidor e o percentual de impostos respectivamente: \n");
  scanf(" %f %f %f",&precoFabrica,&percentualLucro,&impostos);

  //calculos
  impostos=(precoFabrica/100)*impostos;
  percentualLucro=(precoFabrica/100)*percentualLucro;
  custoConsumidor=precoFabrica+percentualLucro+impostos;

  //retorna resultados
  printf("Para o preço de fabricação de: %.2f\nImposto de: %.2f\nLucro de: %.2f\nO custo ao consumidor é: %.2f\n",precoFabrica,impostos,percentualLucro,custoConsumidor);

  
  
  
  return 0;
}