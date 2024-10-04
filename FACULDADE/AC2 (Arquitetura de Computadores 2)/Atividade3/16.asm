.text
.globl _start
_start:
    li s0, 10           # a = 10 
    li s1, 30           # b = 30 
    li s2, 0             # x = 0

    # Checar a >= 0
    blt s0, zero, end_if    # Se a < 0, pula para end_if

    # Checar b <= 50
    li t0, 50          
    bgt s1, t0, end_if    # Se b > 50, pula para end_if

    # Se ambas as condições forem verdadeiras, x = 1
    li s2, 1            # x = 1

end_if:
    # Finalizar o programa
   
