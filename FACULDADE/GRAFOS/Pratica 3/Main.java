import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Encontrar Caminho Mínimo com Menor Número de Arestas ===");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Usar arquivo de entrada personalizado");
        System.out.println("2 - Testar todos os grafos pré-definidos");
        System.out.println("3 - Testar apenas grafos conexos");
        System.out.println("4 - Testar apenas grafos completos");
        System.out.println("5 - Sair");
        System.out.print("Opção: ");
        
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                runWithInputFile(scanner);
                break;
            case 2:
                testAllPredefinedGraphs();
                break;
            case 3:
                testConnectedGraphs();
                break;
            case 4:
                testCompleteGraphs();
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
        
        scanner.close();
    }
    
    private static void runWithInputFile(Scanner scanner) {
        System.out.print("\nDigite o caminho do arquivo de entrada: ");
        scanner.nextLine(); // Consume newline
        String filePath = scanner.nextLine();
        
        try {
            System.out.println("\n=== Executando teste para arquivo personalizado ===");
            long startTime = System.nanoTime();

            //Chama a função main da classe ShortestPathWithMinEdges para executar o algoritmo de caminho minimo
            ShortestPathWithMinEdges.main(new String[]{filePath});
            long endTime = System.nanoTime();
            System.out.println("Tempo de execução: " + (endTime - startTime)/1000000.0 + " ms");
        } catch (Exception e) {
            System.out.println("Erro ao executar: " + e.getMessage());
        }
    }
    
    private static void testAllPredefinedGraphs() {
        System.out.println("\n=== TESTANDO TODOS OS GRAFOS PRÉ-DEFINIDOS ===\n");
        testConnectedGraphs();
        testCompleteGraphs();
    }
    
    private static void testConnectedGraphs() {
        System.out.println("\n=== GRAFOS SIMPLESMENTE CONEXOS ===");
        
        // Pequeno (9 vértices)
        testGraph("Conexo_9.txt", "Conexo Pequeno (9v)");
        
        // Médio (100 vértices)
        testGraph("Conexo_100.txt", "Conexo Médio (100v)");
        
        // Grande (500 vértices)
        testGraph("Conexo_500.txt", "Conexo Grande (500v)");
        
        // Muito Grande (1000 vértices)
        testGraph("Conexo_1000.txt", "Conexo Muito Grande (1000v)");
    }
    
    private static void testCompleteGraphs() {
        System.out.println("\n=== GRAFOS COMPLETOS ===");
        
        // Pequeno (K - 10 vértices)
        testGraph("Completo_10.txt", "Completo K (10v)");
        
        // Médio (K - 100 vértices)
        testGraph("Completo_100.txt", "Completo K (100v)");
        
        // Grande (K - 500 vértices)
        testGraph("Completo_500.txt", "Completo K (500v)");
        
        // Muito Grande (K - 1000 vértices)
        testGraph("Completo_1000.txt", "Completo K (1000v)");
    }
    
    private static void testGraph(String filename, String description) {
        try {
            System.out.println("\n[TESTE] " + description);
            System.out.println("Arquivo: " + filename);
            
            long startTime = System.nanoTime();
            ShortestPathWithMinEdges.main(new String[]{filename});
            long endTime = System.nanoTime();
            
            double duration = (endTime - startTime)/1000000.0; // Converte para  milisegundos
            System.out.printf("Tempo de execução: %.4f ms\n", duration);
            
            // Separador para melhor visualização
            System.out.println("----------------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao testar grafo: " + e.getMessage());
            System.out.println("Certifique-se que o arquivo " + filename + " existe no diretório.");
        }
    }
}