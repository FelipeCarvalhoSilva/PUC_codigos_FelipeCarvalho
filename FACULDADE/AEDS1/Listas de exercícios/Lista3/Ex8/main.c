#include <stdio.h>
#include <math.h>

float funcao(int n,float guarda) {
  for (int i = 1; i <= n; i++) {
    guarda += ((pow(i,2))+1) / (i+3);
  }
  return guarda;
}
int main(void) {
  int n;
  float guarda = 0;
  scanf("%d", &n);
  printf("%f",funcao(n,guarda));
  return 0;
}