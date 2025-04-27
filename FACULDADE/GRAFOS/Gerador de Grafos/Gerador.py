import random
import argparse
from enum import Enum

class GraphType(Enum):
    CONNECTED = "connected"
    COMPLETE = "complete"
    RANDOM = "random"
    TREE = "tree"
    BIPARTITE = "bipartite"

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

def main():
    parser = argparse.ArgumentParser(description="Gerador de Grafos Aleatórios")
    parser.add_argument("--type", type=str, required=True,
                       choices=[t.value for t in GraphType],
                       help="Tipo de grafo a ser gerado")
    parser.add_argument("--vertices", type=int, required=True,
                       help="Número de vértices")
    parser.add_argument("--edges", type=int,
                       help="Número de arestas (não aplicável para grafos completos)")
    parser.add_argument("--output", type=str, required=True,
                       help="Nome do arquivo de saída")
    parser.add_argument("--weight-min", type=int, default=1,
                       help="Valor mínimo para pesos das arestas")
    parser.add_argument("--weight-max", type=int, default=10,
                       help="Valor máximo para pesos das arestas")
    parser.add_argument("--part1-size", type=int,
                       help="Tamanho da partição 1 (apenas para bipartite)")
    parser.add_argument("--part2-size", type=int,
                       help="Tamanho da partição 2 (apenas para bipartite)")
    
    args = parser.parse_args()
    
    weight_range = (args.weight_min, args.weight_max)
    
    try:
        graph_type = GraphType(args.type)
        
        if graph_type == GraphType.CONNECTED:
            if not args.edges:
                args.edges = args.vertices * 2  # Valor padrão
            edges = generate_connected_graph(args.vertices, args.edges, weight_range)
        
        elif graph_type == GraphType.COMPLETE:
            edges = generate_complete_graph(args.vertices, weight_range)
        
        elif graph_type == GraphType.RANDOM:
            if not args.edges:
                args.edges = args.vertices * 2  # Valor padrão
            edges = generate_random_graph(args.vertices, args.edges, weight_range)
        
        elif graph_type == GraphType.TREE:
            edges = generate_tree(args.vertices, weight_range)
        
        elif graph_type == GraphType.BIPARTITE:
            if not args.part1_size or not args.part2_size:
                raise ValueError("Para grafos bipartidos, especifique --part1-size e --part2-size")
            if not args.edges:
                args.edges = min(args.part1_size * args.part2_size, args.part1_size + args.part2_size + 10)
            edges = generate_bipartite_graph(args.part1_size, args.part2_size, args.edges, weight_range)
        
        save_graph_to_file(edges, args.vertices, args.output)
        print(f"Grafo gerado com sucesso e salvo em {args.output}")
        print(f"Tipo: {graph_type.value}, Vértices: {args.vertices}, Arestas: {len(edges)}")
    
    except Exception as e:
        print(f"Erro ao gerar grafo: {str(e)}")

if __name__ == "__main__":
    main()