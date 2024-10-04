    .data
    resultado: .word 0

    .text
    .globl _start
_start:
    li t0, 1            # t0 = 1 (Fibonacci[1] = 1)
    li t1, 1            # t1 = 1 (Fibonacci[2] = 1)
    li t2, 2            # t2 = 2 (índice do próximo termo a calcular)

    # Carregar o número do termo a ser calculado
    li t3, 9        # Carrega o valor 9

    # Calcular a sequência de Fibonacci até o 9º termo
fibonacci_loop:
    add t5, t0, t1      # t5 = t0 + t1 (calcula o próximo termo)
    mv t0, t1           # Atualiza t0 para o valor de t1 (termo anterior)
    mv t1, t5           # Atualiza t1 para o novo termo calculado (próximo termo)
    addi t2, t2, 1      # Incrementa o índice do termo (i++)
    bge t2, t3, end_loop # Se já calculou o 9º termo, sai do loop
    j fibonacci_loop    # Volta para o início do loop para calcular o próximo termo

end_loop:
    la t6, resultado
    sw t1, 0(t6)       # Armazena o resultado final em memória
    # Final
