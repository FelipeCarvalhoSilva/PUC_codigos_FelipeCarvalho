import java.util.Scanner;

public class Main {
    public static boolean contem(int[] array, int num, int n) {
        for (int i = 0; i < (n * n); i++) {
            if (array[i] == num)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int c = scanner.nextInt();
        int passos = 0;
        int casas = 1;
        int atual = ((c * n) + 1 + l);
        int ParOuImpar = passos % 2;
        int[] printados = new int[n * n];
        int index = 0;

        // (L*N)+C+1
        printados[index++] = atual;
        System.out.printf("%d ", atual);
        while (passos < (n * n) && index < 16) {
            // System.out.printf("\n%d-passo\n", passos);
            if (ParOuImpar == 0) {
                for (int j = 0; j <= passos; j++) {
                    if (index >= 16)
                        break;
                    atual = atual + 1;
                    casas++;
                    if (atual <= (n * n) && atual >= 1) {

                        if (!contem(printados, atual, n)) {
                            printados[index++] = atual;
                            System.out.printf("%d ", atual);

                        }
                    }

                }
                for (int j = 0; j <= passos; j++) {
                    if (index >= 16)
                        break;
                    atual = atual + n;
                    casas++;
                    if (atual <= (n * n) && atual >= 1) {
                        if (!contem(printados, atual, n)) {
                            printados[index++] = atual;
                            System.out.printf("%d ", atual);
                        }
                    }
                }

                passos++;
            } else {

                for (int j = 0; j <= passos; j++) {
                    if (index >= 16)
                        break;
                    atual = atual - 1;
                    casas++;
                    if (atual <= (n * n) && atual >= 1) {
                        if (!contem(printados, atual, n)) {
                            printados[index++] = atual;
                            System.out.printf("%d ", atual);
                        }
                    }
                }

                for (int j = 0; j <= passos; j++) {
                    if (index >= 16)
                        break;
                    atual = atual - n;
                    casas++;
                    if (atual <= (n * n) && atual >= 1) {
                        if (!contem(printados, atual, n)) {
                            printados[index++] = atual;
                            System.out.printf("%d ", atual);
                        }
                    }
                }

                passos++;

            }
            ParOuImpar = passos % 2;
        }
        System.out.printf("\n");
        System.out.println(casas);
        scanner.close();
    }
}
