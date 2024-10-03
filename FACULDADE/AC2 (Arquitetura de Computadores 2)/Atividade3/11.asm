.text
.globl _start
_start:
    addi s1, zero, 1  # x = 1
    
    # Carregando o endereço da memória de cada variável em registradores
    la t0, x1         # t0 = endereço de x1
    la t1, x2         # t1 = endereço de x2
    la t2, x3         # t2 = endereço de x3
    la t3, x4         # t3 = endereço de x4
    
    # Carregando os valores armazenados em x1, x2, x3 e x4 nos registradores
    lw t4, 0(t0)      # t4 = valor de x1 (15)
    lw t5, 0(t1)      # t5 = valor de x2 (25)
    lw t6, 0(t2)      # t6 = valor de x3 (13)
    


.data
x1: .word 15         # Armazenando o valor 15 em x1
x2: .word 25         # Armazenando o valor 25 em x2
x3: .word 13         # Armazenando o valor 13 em x3
x4: .word 17         # Armazenando o valor 17 em x4
