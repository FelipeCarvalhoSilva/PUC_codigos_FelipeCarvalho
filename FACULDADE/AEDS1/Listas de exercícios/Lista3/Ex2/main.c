#include <stdio.h>
/*
A prefeitura de uma cidade fez uma pesquisa entre os seus habitantes, coletando
dados sobre o salário e número de filhos. Faça um procedimento que leia esses
dados para um número não determinado de pessoas, calcule e exiba a média de
salário da população (a condição de parada deve ser um flag com salário
negativo). Faça um programa que acione o procedimento
*/

void procedimento(float mediaSalario, float somaSalarios, int n,
                  float salario) {
  // calculos
  while (1) {
    float nada;
    scanf("%f %f", &salario,&nada);
    if (salario <= 0) {
      break;
    }
    somaSalarios += salario;
    n++;
  }
  mediaSalario = somaSalarios / n;

  // retorna resultados
  printf("%.2f\n", mediaSalario);
}

int main(void) {
  // variaveis
  int n = 0;
  float salario = 1, somaSalarios = 0.0, mediaSalario = 0.0;
  procedimento(mediaSalario, somaSalarios, n, salario);
  return 0;
}
