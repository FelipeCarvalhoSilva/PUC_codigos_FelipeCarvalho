.text
.globl _start
_start:
    # Carregar os endereços de x, z e y
    la t0, x          # t0 = endereço de x
    la t1, z          # t1 = endereço de z
    la t2, y          # t2 = endereço de y

    # Carregar os valores de x e z
    lw t3, 0(t0)      # t3 = valor de x
    lw t4, 0(t1)      # t4 = valor de z

    # Calcular 127 * x
    li t5, 127        # t5 = 127
    mul t6, t3, t5    # t6 = 127 * x

    # Calcular 65 * z
    li t7, 65         # t7 = 65
    mul t8, t4, t7    # t8 = 65 * z

    # Subtrair 65 * z de 127 * x
    sub t9, t6, t8    # t9 = 127 * x - 65 * z

    # Adicionar 1 a t9
    addi t9, t9, 1    # t9 = 127 * x - 65 * z + 1

    # Armazenar o resultado de y em memória
    sw t9, 0(t2)      # armazena o valor de y

.data
x: .word 5          
z: .word 7         
y: .word 0         