.data 
A: .word 2,6,20,15
.text
_start:
li s3,4
li s4,0
li s2,0
la s1,A
For1:
bge s2,s3,FimFor1
li t0,2
rem t0,s2,t0
slli t0,s2,2
add t0,s1,t0
lw t1,0(t0)
bne t0,zero,FimIf
addi t1,t1,3
sw t1,0(t0)
FimIf:
slti s4,t1,12
addi,s2,s2,1
FimFor1:
li s4,0
nop
