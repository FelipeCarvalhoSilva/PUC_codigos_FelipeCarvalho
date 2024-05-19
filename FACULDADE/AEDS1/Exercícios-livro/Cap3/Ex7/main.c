#include <stdio.h>

int main(void) {
   /*
  Objetivo- Faça um programa que receba o salário base de um funcionário, calcule e mostre seu salário a receber,
sabendo-se que o funcionário tem gratificação de R$ 50 e paga imposto de 10% sobre o salário base.
  Cap 3 Ex7
  Autor-Felipe
  Data-16/08/23
    */

  //declara variaveis
  float salario,imposto;

  //recebe salario
  printf("Insira seu salario: \n");
  scanf("%f",&salario);

  //calculos
  imposto=(salario/100)*10;
  salario=salario-imposto;

  //salario a receber
  printf("Salario a receber: %.2f$\n",(salario+50));
  printf("Imposto: -%2.f$\nGratificacao de +50$",imposto);
  
  return 0;
}