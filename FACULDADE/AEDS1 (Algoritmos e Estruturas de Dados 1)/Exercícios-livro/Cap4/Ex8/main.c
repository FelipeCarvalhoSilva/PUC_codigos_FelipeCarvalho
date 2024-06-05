#include <stdio.h>
#include <math.h>

int main(void) {
  /*Objetivo-
  Faça um programa que mostre o menu de opções a seguir, receba a opção do usuário e os dados necessários para executar cada operação.

Menu de opções:
1. Somar dois números.
2. Raiz quadrada de um número.
Digite a opção desejada:
  Cap 4 Ex08
  Autor-Felipe
  Data-18/08/23
    */

  //variaveis
  int opcao;
  float numero1,numero2,resultado1;

  //menu de opções e recebe opção selecionada
  printf("1. Somar dois números.\n2. Raiz quadrada de um número.\nDigite a opção desejada:\n");
  scanf(" %d",&opcao);

  //calculo e retorna resultados
  if(opcao==1){
    printf("Você escolheu 1-Somar dois números.\nDigite os numeros: \n");
    scanf(" %f %f",&numero1,&numero2);
    resultado1=numero1+numero2;
    printf("A soma da: %.1f",resultado1);
  }else if(opcao==2){
    printf("Você escolheu 2-Raiz quadrada de um número.\nDigite o numero: \n");
    scanf(" %f",&numero1);
    resultado1=sqrt(numero1);
    printf("A raiz quadrada é: %.1f",resultado1);
  }else{
    printf("Opção invalida, Digite 1 ou 2");
  }
  
  
  
  return 0;
}
