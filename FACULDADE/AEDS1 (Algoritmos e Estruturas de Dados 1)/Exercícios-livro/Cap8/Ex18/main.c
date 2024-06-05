#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void preenche(int* matriz)
{
  srand((unsigned)time(NULL));
  for (int i=0; i < 6; i++)
  {
    for (int j=0; j < 6; j++)
    {
      *(matriz + (i*6) + j) = rand()%51;
    }
  }
}

void exibe (int* tabela)
{
  printf("Valores\n");
  for (int x=0; x < 6; x++)
  {
    for (int y=0; y < 6; y++)
    {
      printf ("%d\t",*(tabela + (x*6) +y));
    }
    printf("\n");
  }
}

int menorDiagSec (int* valores)
{
  int menor = *(valores +(0*6)+5);
  for (int ln=1; ln < 6; ln++)
  {
    if (*(valores +(ln*6)+(6-1-ln)) < menor)
      menor = *(valores +(ln*6)+(6-1-ln));
  }
  return menor;
}

int main(void) 
{
  int* mat = (int*) malloc (6*6*sizeof(int));

  preenche(mat);
  exibe(mat);
  printf("Menor número da diagonal secundárioa é %d\n",menorDiagSec(mat));

  return 0;
}
