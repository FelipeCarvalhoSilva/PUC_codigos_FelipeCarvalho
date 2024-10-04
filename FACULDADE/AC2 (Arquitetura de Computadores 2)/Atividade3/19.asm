.text
.globl _start
_start:

#s0 = i
#t0 = 8
#s1 = x
    li s0, 8   
    li t0, 8   
    while:       
    bne s0, t0, fim  
    # i++
    addi s0, s0, 1 
    mv s1, s0  
    j while

fim:
 # Final
