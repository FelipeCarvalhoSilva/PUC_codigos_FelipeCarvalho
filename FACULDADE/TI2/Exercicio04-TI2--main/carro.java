package exercicio02;

public class carro {
    private int codigo;
    private String nome;
    private String marca;
    private char nota;

    // Construtor
    public carro(int codigo, String nome, String marca, char nota) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.nota = nota;
    }

    // Métodos getters e setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public char getNota() {
        return nota;
    }

    public void setNota(char nota) {
        this.nota = nota;
    }

    // Método toString para representação textual do objeto
    @Override
    public String toString() {
        return "carro{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", nota=" + nota +
                '}';
    }
}
