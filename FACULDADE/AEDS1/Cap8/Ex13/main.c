#include <stdio.h>
// transforma o vetor A em vetor B que é o fatorial do vetor A.
int fatorial(int n) {
  int fatorial = 1;
  for (int i = n; i > 1; i--) {
    fatorial *= i;
  }
  return fatorial;
}
void subrotina(int a[]) {
  int b[3];
  for (int i = 0; i < 3; i++) {
    b[i] = fatorial(a[i]);
    printf("%d\n", b[i]);
  }
}
int main(void) {
  int vetorA[3];
  int vetorB[3];
  for (int i = 0; i < 3; i++) {
    scanf("%d", &vetorA[i]);
  }
  subrotina(vetorA);

  return 0;
}