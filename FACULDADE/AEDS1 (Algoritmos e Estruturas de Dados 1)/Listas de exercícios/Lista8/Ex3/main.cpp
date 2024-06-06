#include <iostream>
using namespace std;

// classe carro
typedef class carro {
private:
  int capacidadeTanque;
  int combustivelAtual;
  int distanciaPercorrida;
  int consumo;

public:
  carro(int combustivel, int distancia) {
    setConsumoCombustivel();
    setCapacidade();
    setDistancia(distancia);
    setCombustivel(combustivel);
  }
  void setDistancia(int novo) { this->distanciaPercorrida = novo; }
  void setCombustivel(int novo) { this->combustivelAtual = novo; }
  void abastecer(int combustivel) { this->combustivelAtual += combustivel; }
  void setCapacidade() { this->capacidadeTanque = 50; }
  void setConsumoCombustivel() { this->consumo = 15; }
  int getCombustivel() { return (combustivelAtual); }
  int getConsumo() { return (consumo); }
  int getDistancia() { return (distanciaPercorrida); }
  void exibeDistanciaPercorrida() {
    if (getCombustivel() * getConsumo() >= getDistancia()) {
      cout << "Distância percorrida: " << to_string(getDistancia()) << "\n"
           << "Combustível restante: "
           << to_string(getCombustivel() - (getDistancia() / getConsumo()))
           << endl;
    } else {
      cout << "Distância percorrida: "
           << to_string(getConsumo() * getCombustivel()) << "\n"
           << "Combustível restante: 0" << endl;
    }
  }
} Carro;
int main() {
  int combustivelCarro1, combustivelCarro2, distanciaCarro1, distanciaCarro2;

  cin >> combustivelCarro1 >> combustivelCarro2 >> distanciaCarro1 >>
      distanciaCarro2;
  Carro carro1(combustivelCarro1, distanciaCarro1);
  Carro carro2(combustivelCarro2, distanciaCarro2);

  cout << "Carro 1"
       << ":\n";
  carro1.exibeDistanciaPercorrida();
  cout << "\n";
  cout << "Carro 2"
       << ":\n";
  carro2.exibeDistanciaPercorrida();
  cout << "\n";
}