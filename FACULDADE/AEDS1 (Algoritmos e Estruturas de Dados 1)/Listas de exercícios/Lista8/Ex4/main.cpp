#include <iostream>
using namespace std;

// classe autor
typedef class autor {
private:
  string nome;

public:
  autor(string nome) { setNome(nome); }
  void setNome(string novo) { this->nome = novo; }
  void exibeNome() { cout << "Autor: " << getNome() << endl; }
  string getNome() { return (nome); }
} Autor;

// classe livro
typedef class livro {
private:
  string titulo;
  int anoPublicado;
  string *nomeAutor;

public:
  livro(string titulo, int ano, string *nome) {
    setTitulo(titulo);
    setAno(ano);
    setAutor(nome);
  }
  void setTitulo(string novo) { this->titulo = novo; }
  void setAno(int novo) { this->anoPublicado = novo; }
  void setAutor(string *novo) { this->nomeAutor = novo; }
  string getTitulo() { return (titulo); }
  int getAno() { return (anoPublicado); }
  void ExibeLivro() {
    cout << "Título: " << getTitulo() << endl
         << "Ano de Publicação: " << to_string(getAno()) << endl;
  }
} livro;

// main
int main() {

  string nomeAutor, nomeLivro;
  int anoPublicado;

  std::getline(std::cin >> std::ws, nomeAutor);
  std::getline(std::cin, nomeLivro);
  std::cin >> anoPublicado;
  Autor autor(nomeAutor);
  livro livro(nomeLivro, anoPublicado, &nomeAutor);

  cout << "Detalhes do Livro:\n";
  livro.ExibeLivro();
  autor.exibeNome();
}