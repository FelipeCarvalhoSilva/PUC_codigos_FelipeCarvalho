.text
.globl _start
_start:
    li s0, 10          # x = 10 (x pode ser qualquer valor)
    li s1, 5           # y = 5  (y pode ser qualquer valor)

    # Inicializar m como 0 
    li s2, 0           # m = 0

    # Comparação if (x > y)
    bgt s0, s1, maior   # if x > y vai para ‘maior’

    # Caso contrário, m = y
    mv s2, s1            # m = y (s2 = s1)
    j end_if             # Pular para o fim da condicional

maior:
    mv s2, s0            # m = x (s2 = s0)

end_if:
    # Finalizar o programa
