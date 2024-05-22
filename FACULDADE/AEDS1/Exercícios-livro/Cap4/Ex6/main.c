#include <stdio.h>

int main(void) {
   /*Objetivo-
  Faça um programa que receba um número inteiro e verifique se é par ou ímpar.
  Cap 4 Ex06
  Autor-Felipe
  Data-18/08/23
    */

  //variaveis
  int numero;

  //recebe valores
  printf("Coloque um Numero INTEIRO.\n");
  scanf(" %d",&numero);

  //caluculo e retorna resultados
  if((numero%2)!=0){
    printf("O número %d é ímpar",numero);
  }else{
    printf("O número %d é par",numero);
  }
 
  return 0;
}
