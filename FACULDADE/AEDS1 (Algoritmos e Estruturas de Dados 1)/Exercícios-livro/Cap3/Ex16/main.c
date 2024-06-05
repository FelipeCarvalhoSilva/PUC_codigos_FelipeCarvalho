#include <stdio.h>

int main(void) {
   /*
  Objetivo-
  Faça um programa que receba o número de horas trabalhadas e o valor do salário mínimo, calcule e mostre o salário a receber, seguindo estas regras:
a) a hora trabalhada vale a metade do salário mínimo.
b) o salário bruto equivale ao número de horas trabalhadas multiplicado pelo valor da hora trabalhada.
c) o imposto equivale a 3% do salário bruto.
d) o salário a receber equivale ao salário bruto menos o imposto.
  Cap 3 Ex16
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  float horas,salarioMinimo,salarioHora,salarioBruto,imposto,salarioReceber;

  //recebe horas, e valor do salario minimo
  printf("Coloque as horas trabalhadas e o valor do salario minimo\n");
  scanf(" %f %f",&horas,&salarioMinimo);

  //calculos
  salarioHora=salarioMinimo/2;
  salarioBruto=horas*salarioHora;
  imposto=(salarioBruto/100)*3;
  salarioReceber=salarioBruto-imposto;

  //resultados
  printf("Com %.1f horas trabalhadas.\nSalario minimo de: $%.2f\nSalario bruto de: $%.2f\nImposto descontado de: -$%.2f\nO salario a receber é: $%.2f\n",horas,salarioMinimo,salarioBruto,imposto,salarioReceber);
  return 0;
}