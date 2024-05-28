#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    int n;
    float input;
    FILE *arqWrite = fopen("texto", "w");

    // Lê o número de valores a serem escritos no arquivo
    scanf("%d", &n);

    // Escreve os valores no arquivo
    for (int i = 0; i < n; i++) {
        scanf("%f", &input);
        fprintf(arqWrite, "%.3f\n", input);
    }
    fclose(arqWrite);

    FILE *arqRead = fopen("texto", "r");
    float *valores = (float *)malloc(n * sizeof(float));

    // Lê os valores do arquivo
    for (int i = 0; i < n; i++) {
        fscanf(arqRead, "%f", &valores[i]);
    }
    fclose(arqRead);

    // Imprime os valores na ordem inversa
    for (int i = n - 1; i >= 0; i--) {
        // Verifica se o número tem casas decimais e imprime formatado
        if ((int)(valores[i] * 1000) % 1000 == 0) {
            printf("%.0f\n", valores[i]);
        } else {
            printf("%.3f\n", valores[i]);
        }
    }

    return 0;
}
