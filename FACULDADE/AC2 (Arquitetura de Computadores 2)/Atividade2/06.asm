start: 
li t0, 1        # Carrega 1 no registrador t0
slli t0, t0, 20 # t0 = 1 << 20 (t0 = 1048576)
li t1, 1        # Carrega 1 no registrador t1
slli t1, t1, 12 # t1 = 1 << 12 (t1 = 4096)
add t2, t0, t1  # t2 = x + y (t2 = 1048576 + 4096 = 1084672)
nop
