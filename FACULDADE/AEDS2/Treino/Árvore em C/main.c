#include <stdio.h>
#include <stdlib.h>

typedef struct no {
  int elemento;
  struct no *esq;
  struct no *dir;
} no;

typedef struct arvore {
  struct no *raiz;

} arvore;

arvore arvoreNova() {
  arvore nova;
  nova.raiz = NULL;
  return nova;
}

no *novoNo(int elemento) {
  no *novoN = (no *)malloc(sizeof(no));

  novoN->elemento = elemento;
  novoN->esq = NULL;
  novoN->dir = NULL;
  return novoN;
}

void inserir(int elemento, no **raiz) {
  if (*raiz == NULL) {
    *raiz = novoNo(elemento);
  } else {
    no *atual = *raiz;
    no *pai = NULL;
    while (atual != NULL) {
      pai = atual;
      if (elemento > atual->elemento) {
        atual = atual->dir;
      } else {
        atual = atual->esq;
      }
    }
    if (elemento > pai->elemento) {
      pai->dir = novoNo(elemento);
    } else {
      pai->esq = novoNo(elemento);
    }
  }
}
void print(no *raiz) {
  if (raiz != NULL) {
    print(raiz->esq);
    printf("%d\n", raiz->elemento);
    print(raiz->dir);
  }
}

int main(void) {
  arvore *tree = (arvore *)malloc(sizeof(arvore));
  tree->raiz = novoNo(9);
  inserir(1, &tree->raiz);
  inserir(9, &tree->raiz);  // Insere 9 como a raiz da árvore
  inserir(1, &tree->raiz);  // Insere 1 à esquerda da raiz
  inserir(5, &tree->raiz);  // Insere 5 à direita de 1
  inserir(12, &tree->raiz); // Insere 12 à direita da raiz
  inserir(10, &tree->raiz); // Insere 10 à esquerda de 12
  print(tree->raiz);

  return 0;
}
