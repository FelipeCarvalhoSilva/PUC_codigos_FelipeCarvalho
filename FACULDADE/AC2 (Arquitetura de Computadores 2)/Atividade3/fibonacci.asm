    .data
sum:    .word 0          # Memory location to store the sum

    .text
    .globl main
main:
    li      t0, 1        # Initialize counter i to 1
    li      t1, 10       # Set upper limit (n = 10)
    li      t2, 0        # Initialize sum to 0

for_loop:
    add     t2, t2, t0        # sum += i
    addi    t0, t0, 1         # i++

    j      for_loop          # Jump back to the start of the loop

