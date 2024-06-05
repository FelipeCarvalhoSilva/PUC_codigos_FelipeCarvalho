#include <stdbool.h>
#include <stdio.h>


// checa se  e triangulo
bool eTriangulo(int a, int b, int c) {
  if (a <= b + c && b <= a + c && c <= a + b && a > 0 && b > 0 && c > 0)
    return true;
  else
    return false;
}

// tipo de triangulo
int tipoTriangulo(int l1, int l2, int l3) {
  if (l1 == l2 && l2 == l3)
    return 1;
  else if (l1 == l2 || l1 == l3 || l2 == l3)
    return 2;
  else
    return 3;
}
// main
int main(void) {
  int lado1, lado2, lado3;
  while (1) {
    scanf("%d %d %d", &lado1, &lado2, &lado3);
    if (lado1 < 0 || lado2 < 0 || lado3 < 0)
      break;
    if (eTriangulo(lado1, lado2, lado3)) {
      switch (tipoTriangulo(lado1, lado2, lado3)) {
      case 1:
        printf("TRIANGULO EQUILATERO\n");
        break;
      case 2:
        printf("TRIANGULO ISOSCELES\n");
        break;
      case 3:
       printf("NÃO TRIANGULO\n");
        break;
      default:printf("NÃO TRIANGULO\n");
        break;
      }
    } else
      printf("NÃO TRIANGULO\n");
  }
  return 0;
}