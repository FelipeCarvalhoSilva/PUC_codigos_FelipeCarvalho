#include <stdio.h>
typedef struct retangulo {

  float largura;
  float altura;
  float area;
} retangulo;
void area(float *largura, float *altura, float *area) {
  *area = *largura * *altura;
}
int main(void) {

  int n;
  scanf("%d", &n);
  retangulo retangulo[n];
  for (int i = 0; i < n; i++) {
    scanf("%f %f", &retangulo[i].largura, &retangulo[i].altura);
    area(&retangulo[i].largura, &retangulo[i].altura, &retangulo[i].area);
  }
  for (int i = 0; i < n; i++) {
    printf("A área do retângulo é: %.2f\n", retangulo[i].area);
  }

  return 0;
}