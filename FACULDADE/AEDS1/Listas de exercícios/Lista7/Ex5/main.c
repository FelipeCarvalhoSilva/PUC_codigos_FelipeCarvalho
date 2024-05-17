#include <stdio.h>
typedef struct guarda {
  char nome[100];
  char endereco[100];
  char telefone[100];
} guarda;
int main(void) {
  guarda clientes[2];
  for (int i=0;i<2;i++){
    scanf(" %[^\n]",clientes[i].nome);
    scanf(" %[^\n]",clientes[i].endereco);
    scanf(" %[^\n]",clientes[i].telefone);
  }
  for (int i=0;i<2;i++){
    printf("%s ",clientes[i].nome);
    printf("%s ",clientes[i].endereco);
    printf("%s\n",clientes[i].telefone);
  }
  
  return 0;
}