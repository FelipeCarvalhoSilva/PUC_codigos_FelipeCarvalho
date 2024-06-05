#include <stdio.h>

int main(void) {
  FILE *arqRecebe = fopen("temp", "w");
  FILE *arqLeitura = fopen("temp", "r");
  char recebe[25];
  fgets(recebe, sizeof(recebe), stdin);
  fprintf(arqRecebe, "%s", recebe);
  fclose(arqRecebe);
  char a = ' ';
  int contaA = 0;
  while (fscanf(arqLeitura, " %c", &a) != EOF) {
    if (a == 'a')
      contaA++;
  }
  printf("%d CARACTERES\n", contaA);
  return 0;
}