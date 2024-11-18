#include <stdio.h>

// Função recursiva para calcular o fatorial
unsigned long long fatorial(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return n * fatorial(n - 1);
}

int main() {
    int numero;

    // Solicitar o número ao usuário
    printf("Digite um número para calcular o fatorial: ");
    scanf("%d", &numero);

    // Validar se o número é não negativo
    if (numero < 0) {
        printf("Erro: O número deve ser não negativo.\n");
        return 1;
    }

    // Calcular o fatorial
    unsigned long long resultado = fatorial(numero);

    printf("Fatorial de %d é: %llu\n", numero, resultado);

    return 0;
}
