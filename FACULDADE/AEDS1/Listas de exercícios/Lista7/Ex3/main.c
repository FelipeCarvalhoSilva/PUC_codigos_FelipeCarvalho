#include <stdbool.h>
#include <stdio.h>

typedef struct cadastro {
  int codigo;
  char email[50];
  int horas;
  char pagina;
  float preco;
} cadastro;
int main(void) {
  int n;
  int temp;
  cadastro cadastros[500];
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf(" %d", &cadastros[i].codigo);
    scanf(" %[^\n]", cadastros[i].email);
    scanf(" %d", &cadastros[i].horas);
    scanf(" %c", &cadastros[i].pagina);
    if (cadastros[i].horas <= 20)
      cadastros[i].preco += 35.0;
    else if (cadastros[i].horas > 20) {
      cadastros[i].preco += 35.0;
      temp = cadastros[i].horas - 20;
      for (int o = 0; o < temp; o++) {
        cadastros[i].preco += 2.5;
      }
    }
    if (cadastros[i].pagina == 'S')
      cadastros[i].preco += 40;
  }
  for (int i = 0; i < n; i++) {
    printf("Cliente %d:\n", i + 1);
    printf("Codigo: %d\n", cadastros[i].codigo);
    printf("E-mail: %s\n", cadastros[i].email);
    printf("Horas de Acesso: %d\n", cadastros[i].horas);
    printf("Possui Pagina: %c\n", cadastros[i].pagina);
    printf("Valor a Pagar: %.2f Quanzas\n\n", cadastros[i].preco);
  }
  return 0;
}