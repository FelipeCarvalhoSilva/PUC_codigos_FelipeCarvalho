.data
    A:  .word 0, 0, 0, 0, 0, 0, 0, 0, 0, 0   

.text
.globl _start
_start:
    li s0, 0                # i = 0, armazenado em s0
    la t0, A                # Carrega o endereço base do array A em t0

for_loop:
    li t1, 10               # Carrega o valor 10 em t1 para comparação com i
    bge s0, t1, end_for     # Se i >= 10, sai do loop

    # Verificar se i é par: i % 2 == 0
    andi t2, s0, 1          # t2 = i & 1, verifica se o bit menos significativo é 0 (i é par)
    beqz t2, even_case      # Se i % 2 == 0, salta para even_case

odd_case:
    # A[i] = A[i] * 2
    slli t3, s0, 2          # Multiplica i por 4 (para acessar o deslocamento de A[i])
    add t4, t0, t3          # t4 = endereço de A[i]
    lw t5, 0(t4)            # Carrega A[i] em t5
    slli t5, t5, 1          # A[i] * 2
    sw t5, 0(t4)            # Armazena A[i] * 2 de volta no array
    j continue_loop         # Salta para continuar o loop

even_case:
    # A[i] = A[i] + A[i + 1]
    slli t3, s0, 2          # Multiplica i por 4 (para acessar o deslocamento de A[i])
    add t4, t0, t3          # t4 = endereço de A[i]
    lw t5, 0(t4)            # Carrega A[i] em t5

    addi t6, t3, 4          # t6 = deslocamento para A[i + 1]
    add s1, t0, t6          # s1 = endereço de A[i + 1]
    lw s2, 0(s1)            # Carrega A[i + 1] em s2
    add t5, t5, s2          # A[i] = A[i] + A[i + 1]
    sw t5, 0(t4)            # Armazena A[i] atualizado de volta no array

continue_loop:
    addi s0, s0, 1          # Incrementa i
    j for_loop              # Volta ao início do loop

end_for:
    # Final