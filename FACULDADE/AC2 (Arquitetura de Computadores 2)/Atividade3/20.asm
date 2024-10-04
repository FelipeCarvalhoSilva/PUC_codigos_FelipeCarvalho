.data
    A:  .word 0, 0, 0, 0, 0, 0, 0, 0, 0, 0    # Array A 

.text
.globl _start
_start:
    li s0, 0                 # i = 0,
    la t0, A                 # Carrega o endereço base do array A em t0

for_loop:
    li t1, 10                # Carrega o valor 10 em t1 para comparar com i
    bge s0, t1, end_for      # Se i >= 10 sair

    slli t2, s0, 2           # Multiplica i por 4, tamanho de um int
    add t3, t0, t2           # t3 = endereço de A[i]

    lw t4, 0(t3)             # Carrega A[i] em t4
    addi t4, t4, 1           # Incrementa A[i] em 1
    sw t4, 0(t3)             # Armazena o valor incrementado de volta em A[i]

    addi s0, s0, 1           # Incrementa i
    j for_loop               # Volta para o início do loop

end_for:
    # Final
