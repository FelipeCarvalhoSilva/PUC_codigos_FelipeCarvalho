#include <stdio.h>
/*
Faça uma sub-rotina que receba um único valor representando segundos. Essa
sub-rotina deverá convertê-lo para horas, minutos e segundos. Todas as variáveis
devem ser passadas como parâmetro, não havendo variáveis globais.
*/

// sub-rotina que converte segundos em minutos e horas e os retorna

void conversor(int segundos) {
  // calcula hora
  int hora = segundos / 3600;
  // calcula segundos
  int minutos = (segundos / 60)%60;
  // calcula sobra de minutos na hora
  // calcula sobra de segundos utilizando minutos
  int sobraSegundos = segundos % 60;

  printf("%d:", hora);
  printf("%d", minutos);
  printf(":%d\n", sobraSegundos);
}

int main(void) {
  // variaveis main
  int segundos;

  // recebe valores
  scanf("%d", &segundos);

  // chama sub-rotina
  conversor(segundos);

  return 0;
}