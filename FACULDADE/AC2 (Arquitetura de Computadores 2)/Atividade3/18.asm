.text
.globl _start
_start:

    # Comparar temp com 10
    li t0, 10            
    beq s0, t0, case_10  

    # Comparar temp com 25
    li t0, 25            
    beq s0, t0, case_25  

    # Caso default
default_case:
    li s1, 0             # x = 0
    j end_switch        

case_10:
    li s1, 10            # x = 10
    j end_switch         # Salta para o fim do switch

case_25:
    li s1, 25            # x = 25
    j end_switch        

end_switch:
    # Final

