#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
typedef struct {
  int *caixas;
  int numCaixas;
  bool maior;
} Pilha;

/* typedef struct{
    Pilha* Primeiro;
}fila; */

int main(void) {

  int n = -1, p = -1;
  // n = numero de caixas
  // p = numero de pilhas
  while (n != 0 && p != 0) {
    scanf("%d%d", &n, &p);
    if (n == 0 && p == 0)
      break;
    Pilha pilhas[p];

    int totalCaixas = 0;
    for (int j = 0; j < p; j++) {
      // Quantidade de caixas na pilha atual
      scanf("%d", &pilhas[j].numCaixas);
      totalCaixas += pilhas[j].numCaixas;

      // Alocar memoria para caixas da pilha atual
      pilhas[j].caixas = (int *)malloc(pilhas[j].numCaixas * sizeof(int));

      // Valor das caixas na pilha atual
      for (int x = pilhas[j].numCaixas - 1; x >= 0; x--) {
        scanf("%d", &pilhas[j].caixas[x]);
      }
    }
    int resultado = 0;
    int maior = 0;
    for (int i = 0; i < p; i++) {
      if (pilhas[i].numCaixas > maior)
        pilhas[i].maior = true;
      else if (pilhas[i].numCaixas == maior)
        pilhas[i].maior = false;
      else
        pilhas[i].maior = false;
    }
    for (int i = 0; i < p; i++) {
      for (int j = 0; j < pilhas[i].numCaixas; j++) {
        if (pilhas[i].caixas[0] == 1 && pilhas[i].maior == true) {
          printf("0\n");
          break;
        }

        if (pilhas[i].caixas[j] == 1) {
          printf("%d\n", resultado);
          break;
        }
        resultado++;
      }
    }
  }

  return 0;
}
