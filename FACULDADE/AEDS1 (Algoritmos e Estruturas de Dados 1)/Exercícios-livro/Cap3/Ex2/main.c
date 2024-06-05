#include <stdio.h>

int main(void) {
  /*
  Objetivo - Calcular media aritimetica de 3 notas
  Cap3 Ex2
  Autor-Felipe
  Data-16/08/2023
    */

  //declaracao de variaveis
  float nota1, nota2, nota3;
  float media;
  printf("insira as 3 notas em ordem. \n");

  //leitura das 3 notas
  scanf("%f %f %f",&nota1,&nota2,&nota3);

  //calculo da media
  media = (nota1+nota2+nota3)/3;

  //exibicao da media
  printf("A media aritmetica é: %.2f",media);
  
  return 0;
}