#include <stdio.h>

int main(void) {
   /*
  Objetivo-
  Pedro comprou um saco de ração com peso em quilos. Ele possui dois gatos, para os quais fornece a quantidade de ração em gramas. A quantidade diária de ração fornecida para cada gato é sempre a mesma. Faça um programa que receba o peso do saco de ração e a quantidade de ração fornecida para cada gato, calcule e mostre quanto restará de ração no saco após cinco dias.
  Cap 3 Ex18
  Autor-Felipe
  Data-17/08/23
    */

  //variaveis
  float pesoSaco,gramaGato1,gramaGato2;

  //recebe valores
  printf("Peso do saco de racao em Kg, e quantidade diária para cada gato\n");
  scanf(" %f %f %f",&pesoSaco,&gramaGato1,&gramaGato2);

  //calculos
  gramaGato1=gramaGato1*1000;
  gramaGato2=gramaGato2*1000;

  //resultados
  printf("Apos 5 dias o saco de %.1fKg\nAgora pesa: %.1fKg\n",pesoSaco,pesoSaco-(5*(gramaGato1+gramaGato2)/1000));
  printf("O gato1 comeu: %.1f gramas de ração Ou %.1fKg\nO gato2 comeu: %.1f gramas de ração Ou %.1fKg",gramaGato1*5,(gramaGato1/1000)*5,gramaGato2*5,(gramaGato2/1000)*5);
  return 0;
}