#include <math.h>
#include <stdio.h>

//ESTE TRABALHO ESTA INCOMPLETO, ELE FOI FEITO NA SEGUNDA PARTE DE 2023, TALVEZ UM DIA EU TERMINE ELE.

// Função para calcular a derivada de um polinômio
void derivadaPolinomial() {
  // Defina os coeficientes do polinômio
  float coeficientes[4] = {3, 0, 2, 1};
  int grau = 3; // Grau do polinômio

  float derivadaCoeficientes[4]; // Coeficientes da derivada

  // Calcular a derivada do polinômio
  for (int i = 0; i < grau; i++) {
    derivadaCoeficientes[i] = coeficientes[i] * (grau - i);
  }

  // Mostrar a derivada
  printf("A derivada da função f(x) = ");
  for (int i = 0; i < grau; i++) {
    printf("%.1fx^%d ", derivadaCoeficientes[i], grau - i - 1);
    if (i != grau - 1) {
      printf("+ ");
    }
  }
  printf("\n");
}

// Função para calcular a derivada de uma função exponencial (f(x) = e^x)
void derivadaExponencial() {
  // A derivada da função exponencial f(x) = e^x é ele mesmo
  printf("A derivada da função exponencial f(x) = e^x é f'(x) = e^x\n");
}

// Função para calcular a derivada de uma função trigonométrica (f(x) = sin(x))
void derivadaTrigonometrica() {

  printf(
      "A derivada da função trigonométrica f(x) = sin(x) é f'(x) = cos(x)\n");
}

// Função para calcular a derivada de uma função logarítmica (f(x) = ln(x))
void derivadaLogaritmica() {
  // A derivada da função logarítmica f(x) = ln(x) é 1/x
  printf("A derivada da função logarítmica f(x) = ln(x) é f'(x) = 1/x\n");
}

// Função para calcular a derivada usando a regra do produto
void regraDoProduto() {
  float x = 2; // Valor de x para calcular a derivada

  float f = x * x; // f(x) = x^2
  float g = x;     // g(x) = x

  float df = 2 * x; // Derivada de f(x) = 2x
  float dg = 1;     // Derivada de g(x) = 1

  // Aplicando a regra do produto para calcular a derivada de (f * g)
  float resultado = df * g + f * dg;

  // Resultado
  printf("A derivada da função (x^2 * x) = x é (x)' = %.1f\n", resultado);
}

// Função para calcular a derivada usando a regra do quociente
void regraDoQuociente() {
  float x = 2; // Valor de x para calcular a derivada

  float f = x * x; // f(x) = x^2
  float g = x;     // g(x) = x

  float df = 2 * x; // Derivada de f(x) = 2x
  float dg = 1;     // Derivada de g(x) = 1

  // Aplicando a regra do quociente para calcular a derivada de (f/g)
  float resultado = (df * g - f * dg) / (g * g);

  // Resultado
  printf("A derivada da função (x^2 / x) = x é (x)' = %.1f\n", resultado);
}

int main() {
  int escolha;

  printf("Escolha o tipo de função para calcular a derivada:\n");
  printf("1) Polinomial\n2) Exponencial\n3) Trigonométrica\n4) Logarítmica\n");
  printf("5) Regra do Produto\n6) Regra do Quociente\n");
  scanf("%d", &escolha);

  switch (escolha) {
  case 1:
    derivadaPolinomial();
    break;
  case 2:
    derivadaExponencial();
    break;
  case 3:
    derivadaTrigonometrica();
    break;
  case 4:
    derivadaLogaritmica();
    break;
  case 5:
    regraDoProduto();
    break;
  case 6:
    regraDoQuociente();
    break;
  default:
    printf("Escolha inválida\n");
  }

  return 0;
}
