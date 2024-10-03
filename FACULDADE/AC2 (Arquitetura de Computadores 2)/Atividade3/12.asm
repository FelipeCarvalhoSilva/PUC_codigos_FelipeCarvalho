.text
.globl _start
_start:
    # Carregar os endereços dos valores x1, x2, x3, x4 e soma
    la t0, x1         # t0 = endereço de x1
    la t1, x2         # t1 = endereço de x2
    la t2, x3         # t2 = endereço de x3
    la t3, x4         # t3 = endereço de x4
    la t4, soma       # t4 = endereço de soma
    
    # Carregar os valores de x1, x2, x3 e x4
    lw t5, 0(t0)      # t5 = valor de x1 (15)
    lw t6, 0(t1)      # t6 = valor de x2 (25)
    lw s1, 0(t2)      # s1 = valor de x3 (13)
    lw s2, 0(t3)      # s2 = valor de x4 (17)
    
    # Somar os valores
    add s3, t5, t6    # s3 = x1 + x2
    add s3, s3, s1    # s3 = s3 + x3
    add s3, s3, s2    # s3 = s3 + x4 (s3 contém a soma total)
    
    # Armazenar a soma no registrador s3
    sw s3, 0(t4)      # armazenar o valor de s3 em soma
    
   
.data
x1: .word 15          # Armazenando o valor 15
x2: .word 25          # Armazenando o valor 25
x3: .word 13          # Armazenando o valor 13
x4: .word 17          # Armazenando o valor 17
soma: .word -1        # Inicializando a soma com -1 
