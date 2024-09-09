start:
li t0,2
li t1, 3
li t2, 4
li t3, 5
 add t4, t0, t1 # t4 = a + b
 add t5, t2, t3 # t5 = c + d
 sub t6, t4, t5 # t6 = (a + b) - (c + d)
 sub t4, t0, t1 # t4 = a - b
 add s0, t4, t6 # s0 = a-b+x;
 sub t0, t6, s0 # t0 = x-y;
nop
