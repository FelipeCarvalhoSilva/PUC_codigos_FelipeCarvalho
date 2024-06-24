#include <stdio.h>
#include <stdlib.h>
//TREINANDO ARVORE EM C
// NO
typedef struct No {
  int elemento;
  struct No *esq, *dir;
} No;

// ARVORE
typedef struct {
  No *raiz;
} Arvore;

/* INSERIR com ponteiro duplono No raiz para poder modificar a raiz se a árvore
estiver vazia. */
void inserir(int elemento, No **raiz) {
  if (*raiz == NULL) {
    No *novoNo = (No *)malloc(sizeof(No));
    novoNo->elemento = elemento;
    novoNo->esq = NULL;
    novoNo->dir = NULL;
    *raiz = novoNo;
  } else {
    No *atual = *raiz;
    if (elemento < atual->elemento) { // esq
      inserir(elemento, &atual->esq);
    } else if (elemento > atual->elemento) { // dir
      inserir(elemento, &atual->dir);
    }
  }
}

// Caminhamento em ordem
void imprimir(No *raiz) {
  if (raiz != NULL) {
    imprimir(raiz->esq);
    printf("Caminhamento em ordem: %d\n", raiz->elemento);
    imprimir(raiz->dir);
  }
}

// Caminhamento pos ordem
void imprimirPos(No *raiz) {
  if (raiz != NULL) {
    imprimirPos(raiz->esq);
    imprimirPos(raiz->dir);
    printf("Caminhamento pos ordem: %d\n", raiz->elemento);
  }
}

// Caminhamento pos ordem
void imprimirPre(No *raiz) {
  if (raiz != NULL) {
    printf("Caminhamento pre ordem: %d\n", raiz->elemento);
    imprimirPre(raiz->esq);
    imprimirPre(raiz->dir);
  }
}

int main(void) {

  Arvore *tree = (Arvore *)malloc(sizeof(Arvore));
  tree->raiz = NULL; // inicializa raiz como NULL

  int x, d;
  scanf("%d", &x);

  for (int i = 0; i < x; i++) {
    scanf("%d", &d);
    inserir(d, &tree->raiz);
  }

  imprimirPre(tree->raiz);
  // libera memoria
  free(tree->raiz);
  free(tree);

  return 0;
}
