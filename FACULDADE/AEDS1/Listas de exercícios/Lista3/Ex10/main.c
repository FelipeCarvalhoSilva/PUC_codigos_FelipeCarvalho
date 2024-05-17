#include <stdio.h>
char funcao(int idade) {
  if (idade >= 18)
    return 'A';
  else if (idade <= 17 && idade >= 16)
    return 'B';
  else if (idade <= 15 && idade >= 14)
    return 'C';
  else if (idade <= 13 && idade >= 11)
    return 'D';
  else if (idade <= 10 && idade >= 8)
    return 'E';
  else if (idade <= 7 && idade >= 5)
    return 'F';
  else
    return ' ';
}
int main(void) {
  int idade, n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &idade);
    printf("%c\n", funcao(idade));
  }
  return 0;
}