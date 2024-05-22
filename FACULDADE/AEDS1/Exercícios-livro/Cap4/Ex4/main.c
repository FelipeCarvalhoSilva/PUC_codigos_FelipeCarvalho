#include <stdio.h>

int main(void) {
    /*Objetivo-
  Faça um programa que receba três números e mostre-os em ordem crescente. Suponha que o usuário digitará três números diferentes.
  Cap 4 Ex04
  Autor-Felipe
  Data-18/08/23
    */

  //variaveis
  float numero1,numero2,numero3;

  //recebe valores
  printf("Coloque 3 Numeros:\n");
  scanf(" %f %f %f",&numero1,&numero2,&numero3);

  //calculos e retorna resultados
  
  if(numero1>numero2 && numero1>numero3){
    if (numero2>numero3){
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero1,numero2,numero3);
  }else{
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero1,numero3,numero2);
  }
  }

    if(numero2>numero1 && numero2>numero3){
      if (numero1>numero3){
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero2,numero1,numero3);
  }else{
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero2,numero3,numero1);
  }
  }

  if(numero3>numero2 && numero3>numero1){
    if (numero2>numero1){
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero3,numero2,numero1);
  }else{
    printf("A ordem crescente é: %.1f depois %.1f depois %.1f\n",numero3,numero1,numero2);
  }
  }


  return 0;
}
