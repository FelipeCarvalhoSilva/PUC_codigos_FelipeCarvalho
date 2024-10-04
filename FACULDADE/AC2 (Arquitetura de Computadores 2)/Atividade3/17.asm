.text
.globl _start
_start:
    li s0, 2    
    be s0, 1, case1
    be s0, 2, case2
    be s0, 3, case3
    jmp default_case

case1:
nop
    jmp end_switch

case2:
nop
    jmp end_switch

case3:
nop
    jmp end_switch

default_case:
nop
    jmp end_switch

end_switch:
nop
    # Finalizar o programa
