#include <stdio.h>

int main(void) {
  /*Objetivo-
  Faça um programa que determine a data cronologicamente maior entre duas datas fornecidas pelo usuário. Cada data deve ser composta por três valores inteiros, em que o primeiro representa o dia, o segundo, o mês e o terceiro, o ano.

  Cap 4 Ex10
  Autor-Felipe
  Data-18/08/23
    */

  //variaveis
  int dia,mes,ano,dia2,mes2,ano2;
  
  //recebe dados
  printf("Coloque: dia depois mes e depois ano.\n");
  scanf(" %d %d %d",&dia,&mes,&ano);
  printf("Data 1: %d / %d / %d\n\n",dia,mes,ano);
  printf("Agora a segunda data: dia depois mes e depois ano.\n");
  scanf(" %d %d %d",&dia2,&mes2,&ano2);
  printf("Data 2: %d / %d / %d\n\n",dia2,mes2,ano2);
  
  //calculos e mostra resultados
  if(ano>ano2){
    printf("A data 1 é a mais recente %d / %d / %d\n",dia,mes,ano);
  }
  else if(ano2>ano){
    printf("A data 2 é a mais recente %d / %d / %d\n",dia2,mes2,ano2);
  }
  else{
    if(mes>mes2){
      printf("A data 1 é a mais recente %d / %d / %d\n",dia,mes,ano);
    }
    else if(mes2>mes){
      printf("A data 2 é a mais recente %d / %d / %d\n",dia2,mes2,ano2);
    }
    else{
      if(dia>dia2){
        printf("A data 1 é a mais recente %d / %d / %d\n",dia,mes,ano);
      }
      else if(dia2>dia){
        printf("A data 2 é a mais recente %d / %d / %d\n",dia2,mes2,ano2);
      }
      else{
        printf("As datas inseridas são iguais\n========================");
      }
    }
  }
  
  
  return 0;
}
