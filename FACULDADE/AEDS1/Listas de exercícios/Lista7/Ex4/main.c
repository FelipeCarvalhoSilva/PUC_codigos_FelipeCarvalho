#include <stdio.h>
typedef struct info {
  int codigo;
  char doacao;
  char NomeObra[50];
  char nomeAutor[50];
  char editora[50];
  int area;
} info;
/*
1-Exatas
2-Humanas
3-Biologicas*/
int main(void) {
  info exatas[500];
  info humanas[500];
  info biologicas[500];
  int n = 0, code, conta = 0;
  while (n >= 0) {
    scanf("%d", &n);
    if (n == 1) {
      scanf("%d", &exatas[conta].codigo);
      scanf(" %c", &exatas[conta].doacao);
      scanf(" %[^\n]]", exatas[conta].NomeObra);
      scanf(" %[^\n]]", exatas[conta].nomeAutor);
      scanf(" %[^\n]]", exatas[conta].editora);
    }
    if (n == 2) {
      scanf("%d", &humanas[conta].codigo);
      scanf(" %c", &humanas[conta].doacao);
      scanf(" %[^\n]]", humanas[conta].NomeObra);
      scanf(" %[^\n]]", humanas[conta].nomeAutor);
      scanf(" %[^\n]]", humanas[conta].editora);
    }
    if (n == 3) {
      scanf("%d", &biologicas[conta].codigo);
      scanf(" %c", &biologicas[conta].doacao);
      scanf(" %[^\n]]", biologicas[conta].NomeObra);
      scanf(" %[^\n]]", biologicas[conta].nomeAutor);
      scanf(" %[^\n]]", biologicas[conta].editora);
    }
    conta++;
  }
  int x = 0, contaExatas = 0, contaHumanas = 0, contaBiologicas = 0;
  while (x != -1) {
    scanf("%d", &x);
    for (int i = 0; i < 500; i++) {
      if (exatas[i].codigo == x)
        contaExatas++;

      if (humanas[i].codigo == x)
        contaHumanas++;

      if (biologicas[i].codigo == x)
        contaBiologicas++;
    }
  }
  if (contaExatas > 0)
    for (int y = 0; y < contaExatas; y++)
      printf("Área: Exatas\n");
  if (contaHumanas > 0)
    for (int y = 0; y < contaHumanas; y++)
      printf("Área: Humanas\n");
  if (contaBiologicas > 0)
    for (int y = 0; y < contaBiologicas; y++)
      printf("Área: Biológicas\n");
  return 0;
}