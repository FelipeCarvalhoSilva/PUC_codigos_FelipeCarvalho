#include <stdio.h>

int main(void) {

  typedef struct {
    char nome[50];
    int dia;
    int mes;
  } Pessoa;

  Pessoa pessoas[40];

  for (int i = 0; i < 40; i++) {

    scanf(" %[^\n]", pessoas[i].nome); // Lê o nome da pessoa

    scanf(" %d", &pessoas[i].dia); // Lê o dia de aniversário

    scanf(" %d", &pessoas[i].mes); // Lê o mês de aniversário
  }
  for (int mes = 1; mes <= 12; mes++) {
    printf("\nAniversariantes do mes %d:\n", mes);
    for (int o = 0; o < 40; o++) {
      if (pessoas[o].mes == mes) {
        printf("Nome: %s, Dia: %d\n", pessoas[o].nome, pessoas[o].dia);
      }
    }
  }

  return 0;
}