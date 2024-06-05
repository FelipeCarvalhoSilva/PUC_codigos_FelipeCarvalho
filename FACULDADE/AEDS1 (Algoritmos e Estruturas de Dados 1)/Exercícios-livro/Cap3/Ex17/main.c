#include <stdio.h>

int main(void) {
   /*
  Objetivo-
  Um trabalhador recebeu seu salário e o depositou em sua conta bancária. Esse trabalhador emitiu dois cheques e agora deseja saber seu saldo atual. Sabe-se que cada operação bancária de retirada paga CPMF de 0,38% e o saldo inicial da conta está zerado.
  Cap 3 Ex17
  Autor-Felipe
  Data-16/08/23
    */

  //variaveis
  float conta,cheque1,cheque2,imposto,salario,salarioDescontado,chequeDesconto1,chequeDesconto2;

  //recebe valores
  printf("Insira valor do Salario recebido, e de 2 cheques de reatirada.");
  scanf(" %f %f %f",&salario,&cheque1,&cheque2);

  //contas
  imposto=0.38/100;
  salarioDescontado=imposto*salario;
  chequeDesconto1=cheque1*imposto;
  chequeDesconto2=cheque2*imposto;
  conta= salario-(salarioDescontado);
  conta-=cheque1;
  conta-=cheque2;

  //resultados
  printf("Com um salario de: $%.2f\nDescontando CPMF de: -$%.2f\nDesconto cheques de retirada: -$%.2f e -$%.2f\n",salario,salarioDescontado,chequeDesconto1,chequeDesconto2);
  printf("Saldo atual da conta: $%.2f",conta);
  
  return 0;
}