#include <stdio.h>
#include <string.h>

// Faça um programa para criar um arquivo chamado ALUNOS.DAT, no qual cada
// registro será composto pelos seguintes campos: numero, nome, curso, nota1,
// nota2.

int main(void) {
  char texto[6];
  int n = 1,aux;
  FILE *alunos = fopen("alunos.dat", "w");
  while (n > 0) {
    scanf("%d", &n);
    if (n > 0)
      fprintf(alunos, "%d ", n);
  }
  fclose(alunos);

  return 0;
}