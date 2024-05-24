#include <stdio.h>
/*
Crie um programa que leia a quantidade de veículos que uma locadora de veículos
possui e o valor que ela cobra por cada aluguel, mostrando as informações
pedidas a seguir: 
a) Sabendo-se que um terço dos veículos são alugados por mês,
exiba o faturamento anual da locadora; 
b) Quando o cliente atrasa a entrega, é
cobrada uma multa de 20% sobre o valor do aluguel. Sabendo que um décimo dos
veículos alugados no mês é devolvido com atraso, calcule o valor ganho com
multas no mês; 
c) Sabe-se ainda que 2% dos veículos precisam de manutenção ao
longo do ano. Calcule o valor gasto com a manutenção anual, sabendo que o valor
gasto em média com a manutenção é de R$ 600,00. Além de mostrar os resultados na
tela, grave em um arquivo chamado resultado.txt. Cada valor deverá ser
armazenado em uma linha.
*/
int main(void) {
  FILE *result = fopen("resultado.txt", "w");
  int carros;
  float aluguel, faturamentoAnual, multa, manutencao;
  scanf("%d %f", &carros, &aluguel);
  multa = ((((carros / 3) / 10) * aluguel)) * 20 / 100;
  manutencao = ((carros/100)*2)*600.00+600;
  faturamentoAnual = (((carros / 3) * aluguel) * 12)+(multa*12)-(manutencao);
  printf("%.2f\n%.2f\n%.2f\n",faturamentoAnual,multa,manutencao);
  fprintf(result,"%.2f\n",faturamentoAnual);
  fprintf(result,"%.2f\n",multa);
  fprintf(result,"%.2f\n",manutencao);
  fclose(result);
  return 0;
}