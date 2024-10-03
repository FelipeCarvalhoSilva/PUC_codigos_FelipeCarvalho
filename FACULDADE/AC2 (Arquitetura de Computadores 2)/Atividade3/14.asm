.text
.globl _start
_start:
    # Carregar os endereços base de A e B nos registradores
    la t0, A           # t0 contém o endereço base de A
    la t1, B           # t1 contém o endereço base de B

    # A[0] = B[0] * 1 + A[0]
    lw t2, 0(t1)       # t2 = B[0] (carrega o valor de B[0])
    lw t3, 0(t0)       # t3 = A[0] (carrega o valor de A[0])
    add t3, t3, t2     # t3 = A[0] + B[0] * 1
    sw t3, 0(t0)       # Armazena o resultado em A[0]

    # A[1] = B[1] * 2 + A[1]
    lw t2, 4(t1)       # t2 = B[1] (carrega o valor de B[1])
    lw t3, 4(t0)       # t3 = A[1] (carrega o valor de A[1])
    li t4, 2           # t4 = 2 (multiplicador)
    mul t2, t2, t4     # t2 = B[1] * 2
    add t3, t3, t2     # t3 = A[1] + B[1] * 2
    sw t3, 4(t0)       # Armazena o resultado em A[1]

    # A[2] = B[2] * 3 + A[2]
    lw t2, 8(t1)       # t2 = B[2] (carrega o valor de B[2])
    lw t3, 8(t0)       # t3 = A[2] (carrega o valor de A[2])
    li t4, 3           # t4 = 3 (multiplicador)
    mul t2, t2, t4     # t2 = B[2] * 3
    add t3, t3, t2     # t3 = A[2] + B[2] * 3
    sw t3, 8(t0)       # Armazena o resultado em A[2]

    # A[3] = B[3] * 4 + A[3]
    lw t2, 12(t1)      # t2 = B[3] (carrega o valor de B[3])
    lw t3, 12(t0)      # t3 = A[3] (carrega o valor de A[3])
    li t4, 4           # t4 = 4 (multiplicador)
    mul t2, t2, t4     # t2 = B[3] * 4
    add t3, t3, t2     # t3 = A[3] + B[3] * 4
    sw t3, 12(t0)      # Armazena o resultado em A[3]

    # A[4] = B[4] * 5 + A[4]
    lw t2, 16(t1)      # t2 = B[4] (carrega o valor de B[4])
    lw t3, 16(t0)      # t3 = A[4] (carrega o valor de A[4])
    li t4, 5           # t4 = 5 (multiplicador)
    mul t2, t2, t4     # t2 = B[4] * 5
    add t3, t3, t2     # t3 = A[4] + B[4] * 5
    sw t3, 16(t0)      # Armazena o resultado em A[4]

    # Finalizar o programa
    li a7, 93          # Código de ECALL para encerrar
    ecall

.data
A: .word 1, 3, 5, 7, 9   # Array A
B: .word 2, 4, 6, 8, 10  # Array B
