#include <stdio.h>

void procedimento() {
  int numero1, numero2, numero3;
  scanf("%d %d %d", &numero1, &numero2, &numero3);
  
if(numero1<numero2 && numero1<numero3){
    if (numero2<numero3){
    printf("%d %d %d\n",numero1,numero2,numero3);
  }else{
    printf("%d %d %d\n",numero1,numero3,numero2);
  }
  }

    if(numero2<numero1 && numero2<numero3){
      if (numero1<numero3){
    printf("%d %d %d\n",numero2,numero1,numero3);
  }else{
    printf("%d %d %d\n",numero2,numero3,numero1);
  }
  }

  if(numero3<numero2 && numero3<numero1){
    if (numero2<numero1){
    printf("%d %d %d\n",numero3,numero2,numero1);
  }else{
    printf("%d %d %d\n",numero3,numero1,numero2);
  }
  }
}

int main(void) {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    procedimento();
  }
  return 0;
}