#include <stdio.h>
typedef struct ponto {
  int x1;
  int y1;
  int x2;
  int y2;
  int x3;
  int y3;
  int alinhamentoHorizontal;
  int alinhamentoVertical;
} ponto;
int main(void) {
  int n, alinhamentoHorizontal = 0, alinhamentoVertical = 0;
  scanf("%d", &n);
  ponto pontos[n];
  for (int i = 0; i < n; i++) {
    pontos[i].alinhamentoVertical = 0;
    pontos[i].alinhamentoHorizontal = 0;
    scanf("%d %d", &pontos[i].x1, &pontos[i].y1);
    scanf("%d %d", &pontos[i].x2, &pontos[i].y2);
    scanf("%d %d", &pontos[i].x3, &pontos[i].y3);
    if (pontos[i].x1 == pontos[i].x2)
      pontos[i].alinhamentoHorizontal++;
    if (pontos[i].x2 == pontos[i].x3)
      pontos[i].alinhamentoHorizontal++;
    if (pontos[i].x1 == pontos[i].x3)
      pontos[i].alinhamentoHorizontal++;

    if (pontos[i].y1 == pontos[i].y2)
      pontos[i].alinhamentoVertical++;
    if (pontos[i].y2 == pontos[i].y3)
      pontos[i].alinhamentoVertical++;
    if (pontos[i].y1 == pontos[i].y3)
      pontos[i].alinhamentoVertical++;
  }

  // for printador
  for (int i = 0; i < n; i++) {
    // vertical
    if (pontos[i].alinhamentoVertical == 0)
      printf("Nao ha alinhamentos verticais\n");
    else
      printf("alinhamentos verticais: %d\n", pontos[i].alinhamentoVertical);

    // horizontal
    if (pontos[i].alinhamentoHorizontal == 0)
      printf("Nao ha alinhamentos horizontais\n");
    else
      printf("alinhamentos horizontais: %d\n", pontos[i].alinhamentoHorizontal);
  }
  return 0;
}