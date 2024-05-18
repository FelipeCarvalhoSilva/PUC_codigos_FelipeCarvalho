#include <stdio.h>

long int contador(long int n) {
  if ((n / 10) < 1) return 1;
  else return 1 + contador(n / 10);
}

long int contadorNegativos(long int n) {
  if ((n / 10) > -1) return 1;
  else return 1 + contadorNegativos(n / 10);
}

int main(void) {
  long int n;
  scanf("%ld", &n);
  if (n >= 0) printf("%ld\n", contador(n));
  else printf("%ld\n", contadorNegativos(n));
  return 0;
}
