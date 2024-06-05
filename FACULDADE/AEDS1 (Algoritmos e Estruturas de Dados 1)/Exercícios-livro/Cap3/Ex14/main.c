#include <stdio.h>

int main(void) {
  /*
  Objetivo- calcular idade no ano atual e em 2050
  Cap 3 Ex14
  Autor-Felipe
  Data-16/08/23
    */

  //declaracao variaveis
  int nascimento,atual;
  

  //leitura do ano tual e nascimento
  printf("insira seu ano de nascimento e ano atual em ordem \n");
  scanf("%d %d",&nascimento,&atual);

  //calcula e mostra os dados
  printf("sua idade para o ano: %d é : %d\nPara o ano 2050 sua idade é:%d",atual,(atual-nascimento),(2050-nascimento));
  return 0;
}