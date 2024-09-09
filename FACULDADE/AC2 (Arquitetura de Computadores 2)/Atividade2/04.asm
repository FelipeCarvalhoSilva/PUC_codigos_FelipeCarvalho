start:
li t0,1
li t1,5
slli t1, t0, 2  # t1 = x << 2 (t1 = 1 << 2 = 4)
add t2, t1, t0  # t2 = t1 + x (t2 = 4 + 1 = 5)
li t3, 15     # t3 = 15
add t2, t2, t3 # y = t2 + 15 (t2 = 5 + 15 = 20)
nop