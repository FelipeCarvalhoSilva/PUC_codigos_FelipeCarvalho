#include <stdio.h>
#include <stdlib.h>

// Função para calcular o fatorial de forma iterativa
unsigned long long int fatorialIterativo(int n) {
    if (n < 0) {
        printf("Fatorial não definido para números negativos.\n");
        exit(1);
    }
    unsigned long long int fatorial = 1;
    for (int i = 1; i <= n; i++) {
        fatorial *= i;
    }
    return fatorial;
}

int main(int argc, char *argv[]) {
    // Verifica se o número foi passado como argumento
    if (argc != 2) {
        printf("Uso: %s <numero>\n", argv[0]);
        return 1;
    }

    // Converte o argumento para um número inteiro
    int numero = atoi(argv[1]);

    // Calcula e exibe o fatorial
    unsigned long long int resultado = fatorialIterativo(numero);
    printf("Fatorial iterativo de %d = %llu\n", numero, resultado);

    return 0;
}
