package exercicio03;

import static spark.Spark.*;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        carroDAO carroDAO = new carroDAO();
        Scanner scanner = new Scanner(System.in);
        
        // Configurar rota para inserir um novo carro
        post("/inserirCarro", (request, response) -> {
            int codigo = Integer.parseInt(request.queryParams("codigo"));
            String nome = request.queryParams("nome");
            String marca = request.queryParams("marca");
            char nota = request.queryParams("nota").charAt(0);
            
            carro novoCarro = new carro(codigo, nome, marca, nota);
            boolean inserido = carroDAO.insert(novoCarro);
            
            if (inserido) {
                return "Carro inserido com sucesso!";
            } else {
                response.status(500); // Erro interno do servidor
                return "Falha ao inserir o carro.";
            }
        });
        
        // Configurar rota para listar todos os carros
        get("/listarCarros", (request, response) -> {
            List<carro> carros = carroDAO.get();
            if (carros.isEmpty()) {
                return "Não há carros cadastrados.";
            } else {
                StringBuilder listaCarros = new StringBuilder("Lista de carros:\n");
                for (carro carro : carros) {
                    listaCarros.append(carro.toString()).append("\n");
                }
                return listaCarros.toString();
            }
        });
        
        // Configurar rota para atualizar um carro
        put("/atualizarCarro/:codigo", (request, response) -> {
            int codigo = Integer.parseInt(request.params(":codigo"));
            String nome = request.queryParams("nome");
            String marca = request.queryParams("marca");
            char nota = request.queryParams("nota").charAt(0);
            
            carro carroExistente = carroDAO.get(codigo);
            if (carroExistente == null) {
                response.status(404); // Not Found
                return "Carro não encontrado.";
            }
            
            carroExistente.setNome(nome);
            carroExistente.setMarca(marca);
            carroExistente.setNota(nota);
            
            boolean atualizado = carroDAO.update(carroExistente);
            if (atualizado) {
                return "Carro atualizado com sucesso!";
            } else {
                response.status(500); // Erro interno do servidor
                return "Falha ao atualizar o carro.";
            }
        });
        
        // Configurar rota para excluir um carro
        delete("/excluirCarro/:codigo", (request, response) -> {
            int codigo = Integer.parseInt(request.params(":codigo"));
            
            boolean excluido = carroDAO.delete(codigo);
            if (excluido) {
                return "Carro excluído com sucesso!";
            } else {
                response.status(500); // Erro interno do servidor
                return "Falha ao excluir o carro.";
            }
        });
        
        boolean sair = false;
        
        while (!sair) {
            System.out.println("Escolha uma operação:");
            System.out.println("1) Inserir");
            System.out.println("2) Listar");
            System.out.println("3) Atualizar");
            System.out.println("4) Excluir");
            System.out.println("5) Sair");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            switch (opcao) {
                case 1:
                    inserircarro(carroDAO, scanner);
                    break;
                case 2:
                    // Nada a ser feito aqui, pois a listagem é feita pela rota do Spark
                    break;
                case 3:
                    atualizarcarro(carroDAO, scanner);
                    break;
                case 4:
                    excluircarro(carroDAO, scanner);
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
        
        // Fechar recursos
        carroDAO.finalize();
        scanner.close();
    }
    
    private static void inserircarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        System.out.println("Digite o nome do carro:");
        String nome = scanner.nextLine();
        
        System.out.println("Digite a marca do carro:");
        String marca = scanner.nextLine();
        
        System.out.println("Digite a nota do carro:");
        char nota = scanner.nextLine().charAt(0);
        
        carro novocarro = new carro(codigo, nome, marca, nota);
        boolean inserido = carroDAO.insert(novocarro);
        if (inserido) {
            System.out.println("carro inserido com sucesso.");
        } else {
            System.out.println("Falha ao inserir o carro.");
        }
    }
    
    private static void atualizarcarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro que deseja atualizar:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        carro carroExistente = carroDAO.get(codigo);
        if (carroExistente == null) {
            System.out.println("carro não encontrado.");
            return;
        }
        
        System.out.println("Digite o novo nome do carro:");
        String novoNome = scanner.nextLine();
        
        System.out.println("Digite a nova marca do carro:");
        String novaMarca = scanner.nextLine();
        
        System.out.println("Digite a nova nota do carro:");
        char novaNota = scanner.nextLine().charAt(0);
        
        carroExistente.setNome(novoNome);
        carroExistente.setMarca(novaMarca);
        carroExistente.setNota(novaNota);
        
        boolean atualizado = carroDAO.update(carroExistente);
        if (atualizado) {
            System.out.println("carro atualizado com sucesso.");
        } else {
            System.out.println("Falha ao atualizar o carro.");
        }
    }
    
    private static void excluircarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro que deseja excluir:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        boolean excluido = carroDAO.delete(codigo);
        if (excluido) {
            System.out.println("carro excluído com sucesso.");
        } else {
            System.out.println("Falha ao excluir o carro.");
        }
    }
}
