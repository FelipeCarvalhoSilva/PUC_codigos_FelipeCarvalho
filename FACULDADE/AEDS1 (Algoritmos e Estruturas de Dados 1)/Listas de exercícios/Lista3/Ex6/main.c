#include <stdio.h>

float funcao(int n) {
  if (n == 1)
    return 1;
  else if (n < 1)
    return 0;
  else
    return (n * funcao(n - 1));
}
int main(void) {
  int n;
  float guarda = 0;
  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    guarda += 1 / funcao(i);
  }
  printf("%f", 1 + guarda);
  return 0;
}