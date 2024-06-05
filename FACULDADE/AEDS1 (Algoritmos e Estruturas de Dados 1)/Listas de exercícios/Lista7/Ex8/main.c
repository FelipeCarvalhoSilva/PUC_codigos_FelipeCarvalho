#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int randomiza() {
  time_t t;
  srand(6);
  return (rand() % 100);
}
int main(void) {
  int consumo, combustivel, abastecer, consumoTotal;
  int distancia = randomiza();

  scanf("%d %d", &consumo, &combustivel);

  consumoTotal = distancia * consumo;
  abastecer = consumoTotal / combustivel;
  
  if (abastecer != 0) {
    printf("A moto precisa parar %d vezes para abastecer\n", abastecer-1);
  } else {
    printf("A moto não precisa parar para abastecer\n");
  }
  return 0;
}