start:
li t0,3
li t1,4
li t2, 15     # t2 = 15
sub t2, t2, t0 # t2 = 15 - x (t2 = 15 - 3 = 12)
li t3, 67     # t3 = 67
sub t3, t3, t1 # t3 = 67 - y (t3 = 67 - 4 = 63)
add t4, t2, t3 # t4 = (15 - x) + (67 - y) (t4 = 12 + 63 = 75)
li t5, 4       # t5 = 4
add t4, t4, t5 # z = t4 + 4 (t4 = 75 + 4 = 79)
nop