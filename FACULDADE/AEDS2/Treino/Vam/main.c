#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct pessoa {
  char nome[50];
  char regiao;
  int distancia;
};

int comparar_pessoas(const void *a, const void *b) {
  struct pessoa *p1 = (struct pessoa *)a;
  struct pessoa *p2 = (struct pessoa *)b;

  // compara distancias primeiro
  if (p1->distancia != p2->distancia)
    return p1->distancia - p2->distancia;

  // distancias iguagis, compara regiao
  if (p1->regiao != p2->regiao)
    return p1->regiao - p2->regiao;

  // regioes iguais, compara nomes
  return strcmp(p1->nome, p2->nome);
}

int main(void) {
  int n;
  scanf("%d", &n);

  struct pessoa *pessoas = malloc(n * sizeof(struct pessoa));
  if (pessoas == NULL) {
    printf("Erro de alocação de memória.\n");
    return 1;
  }

  for (int i = 0; i < n; i++) {
    scanf("%s %c %d", pessoas[i].nome, &pessoas[i].regiao,
          &pessoas[i].distancia);
  }

  qsort(pessoas, n, sizeof(struct pessoa), comparar_pessoas);

  for (int i = 0; i < n; i++) {
    printf("%s\n", pessoas[i].nome);
  }

  free(pessoas);
  return 0;
}
