import random
import os
from enum import Enum

class GraphType(Enum):
    CONNECTED = "Conexo"
    COMPLETE = "Completo"
    RANDOM = "Aleatório"
    TREE = "Árvore"
    BIPARTITE = "Bipartido"

def generate_connected_graph(num_vertices, num_edges, weight_range=(1, 10)):
    """Gera um grafo conexo simples"""
    if num_edges < num_vertices - 1:
        num_edges = num_vertices - 1  # Garante conexidade mínima
    
    edges = set()
    
    # Primeiro cria uma árvore para garantir conexidade
    for i in range(1, num_vertices):
        weight = random.randint(*weight_range)
        edges.add((random.randint(0, i-1), i, weight))
    
    # Adiciona arestas extras até atingir o número desejado
    while len(edges) < num_edges:
        u = random.randint(0, num_vertices - 1)
        v = random.randint(0, num_vertices - 1)
        if u != v and not any(e[0] == u and e[1] == v for e in edges):
            weight = random.randint(*weight_range)
            edges.add((u, v, weight))
    
    return edges

def generate_complete_graph(num_vertices, weight_range=(1, 10)):
    """Gera um grafo completo (K_n)"""
    edges = set()
    for u in range(num_vertices):
        for v in range(num_vertices):
            if u != v:
                weight = random.randint(*weight_range)
                edges.add((u, v, weight))
    return edges

def generate_random_graph(num_vertices, num_edges, weight_range=(1, 10)):
    """Gera um grafo aleatório simples"""
    edges = set()
    while len(edges) < num_edges:
        u = random.randint(0, num_vertices - 1)
        v = random.randint(0, num_vertices - 1)
        if u != v and not any(e[0] == u and e[1] == v for e in edges):
            weight = random.randint(*weight_range)
            edges.add((u, v, weight))
    return edges

def generate_tree(num_vertices, weight_range=(1, 10)):
    """Gera uma árvore aleatória"""
    edges = set()
    for i in range(1, num_vertices):
        parent = random.randint(0, i-1)
        weight = random.randint(*weight_range)
        edges.add((parent, i, weight))
    return edges

def generate_bipartite_graph(part1_size, part2_size, num_edges, weight_range=(1, 10)):
    """Gera um grafo bipartido aleatório"""
    edges = set()
    vertices_part1 = list(range(part1_size))
    vertices_part2 = list(range(part1_size, part1_size + part2_size))
    
    while len(edges) < num_edges:
        u = random.choice(vertices_part1)
        v = random.choice(vertices_part2)
        if not any(e[0] == u and e[1] == v for e in edges):
            weight = random.randint(*weight_range)
            edges.add((u, v, weight))
    return edges

def save_graph_to_file(edges, num_vertices, filename, source=0, target=None):
    """Salva o grafo em um arquivo no formato especificado"""
    if target is None:
        target = num_vertices - 1  # Usa o último vértice como destino padrão
    
    with open(filename, 'w') as f:
        f.write(f"{num_vertices} {len(edges)} {source} {target}\n")
        for u, v, w in edges:
            f.write(f"{u} {v} {w}\n")

def clear_screen():
    """Limpa a tela do console"""
    os.system('cls' if os.name == 'nt' else 'clear')

def show_menu():
    """Exibe o menu principal"""
    clear_screen()
    print("=== GERADOR DE GRAFOS ===")
    print("Escolha o tipo de grafo a ser gerado:")
    for i, graph_type in enumerate(GraphType, 1):
        print(f"{i}. {graph_type.value}")
    print(f"{len(GraphType)+1}. Sair")
    print("\nObs: Grafos serão salvos na pasta 'generated_graphs'")

def get_graph_parameters(graph_type):
    """Obtém os parâmetros para geração do grafo"""
    params = {}
    
    if graph_type != GraphType.COMPLETE and graph_type != GraphType.TREE:
        while True:
            try:
                params['edges'] = int(input("Número de arestas: "))
                if params['edges'] <= 0:
                    print("O número de arestas deve ser positivo!")
                    continue
                break
            except ValueError:
                print("Por favor, digite um número válido!")
    
    if graph_type == GraphType.BIPARTITE:
        while True:
            try:
                part1 = int(input("Tamanho da partição 1: "))
                part2 = int(input("Tamanho da partição 2: "))
                if part1 <= 0 or part2 <= 0:
                    print("Os tamanhos devem ser positivos!")
                    continue
                params['part1_size'] = part1
                params['part2_size'] = part2
                params['vertices'] = part1 + part2
                break
            except ValueError:
                print("Por favor, digite números válidos!")
    else:
        while True:
            try:
                params['vertices'] = int(input("Número de vértices: "))
                if params['vertices'] <= 0:
                    print("O número de vértices deve ser positivo!")
                    continue
                break
            except ValueError:
                print("Por favor, digite um número válido!")
    
    while True:
        try:
            min_w = int(input("Peso mínimo das arestas (padrão=1): ") or 1)
            max_w = int(input("Peso máximo das arestas (padrão=10): ") or 10)
            if min_w > max_w:
                print("O peso mínimo não pode ser maior que o máximo!")
                continue
            params['weight_range'] = (min_w, max_w)
            break
        except ValueError:
            print("Por favor, digite números válidos!")
    
    filename = input("Nome do arquivo de saída (sem extensão): ").strip()
    params['filename'] = f"generated_graphs/{filename}.txt"
    
    return params

def generate_graph():
    """Função principal que gerencia a geração de grafos"""
    # Cria diretório para os grafos gerados
    os.makedirs("generated_graphs", exist_ok=True)
    
    while True:
        show_menu()
        try:
            option = int(input("\nOpção: "))
        except ValueError:
            print("Por favor, digite um número válido!")
            input("Pressione Enter para continuar...")
            continue
        
        if option == len(GraphType) + 1:
            print("Saindo...")
            break
        elif 1 <= option <= len(GraphType):
            graph_type = list(GraphType)[option-1]
            print(f"\nGerando grafo {graph_type.value.lower()}")
            
            try:
                params = get_graph_parameters(graph_type)
                
                if graph_type == GraphType.CONNECTED:
                    edges = generate_connected_graph(params['vertices'], params['edges'], params['weight_range'])
                elif graph_type == GraphType.COMPLETE:
                    edges = generate_complete_graph(params['vertices'], params['weight_range'])
                elif graph_type == GraphType.RANDOM:
                    edges = generate_random_graph(params['vertices'], params['edges'], params['weight_range'])
                elif graph_type == GraphType.TREE:
                    edges = generate_tree(params['vertices'], params['weight_range'])
                elif graph_type == GraphType.BIPARTITE:
                    edges = generate_bipartite_graph(
                        params['part1_size'], params['part2_size'], params['edges'], params['weight_range'])
                
                save_graph_to_file(edges, params['vertices'], params['filename'])
                print(f"\nGrafo gerado com sucesso e salvo em {params['filename']}")
                print(f"Tipo: {graph_type.value}")
                print(f"Vértices: {params['vertices']}")
                print(f"Arestas: {len(edges)}")
                print(f"Pesos: {params['weight_range'][0]} a {params['weight_range'][1]}")
                
            except Exception as e:
                print(f"\nErro ao gerar grafo: {str(e)}")
            
            input("\nPressione Enter para continuar...")
        else:
            print("Opção inválida!")
            input("Pressione Enter para continuar...")

if __name__ == "__main__":
    generate_graph()