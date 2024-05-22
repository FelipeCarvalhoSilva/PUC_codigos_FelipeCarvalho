#include <stdio.h>

int main(void) {
   /*Objetivo-
  Faça um programa que receba dois números e mostre o maior.
  Cap 4 Ex03
  Autor-Felipe
  Data-18/08/23
    */

  //variaveis
  float numero1,numero2,maior;

  //recebe valores
  printf("Insira 2 numeros.\n");
  scanf(" %f %f",&numero1,&numero2);
  
  //calculos e retorna resultados
  if(numero1>numero2){
    maior=numero1;
    printf("O maior Numero é: %.1f \n",maior);
  }else if (numero2>numero1){
    maior=numero2;
    printf("O maior Numero é: %.1f \n",maior);
  }else{
    printf("Os numeros inseridos são iguais.\n");
    ;
  }
  return 0;
}
