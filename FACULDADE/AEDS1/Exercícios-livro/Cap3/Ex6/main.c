#include <stdio.h>

int main(void) {
  /*
  Objetivo- Faça um programa que receba o salário base de um funcionário, calcule e mostre o salário a receber, sabendo-se que o funcionário tem gratificação de 5% sobre o salário base e paga imposto de 7% também sobre o salário base
  Cap 3 Ex6
  Autor-Felipe
  Data-16/08/23
    */

  //declaracao de variaveis
  float salario,gratificado,imposto;

  //recebe salario
  printf(" Insira seu salario: \n");
  scanf("%f",&salario);

  //calculos
  gratificado = (salario/100)*5;
  imposto= (salario/100)*7;

  //salario a receber
  printf("Salario a receber: %.2f\n",(salario-imposto)+gratificado);
  printf("Gratificado= +%.2f\nImposto= -%.2f",gratificado,imposto);
  
  return 0;
}