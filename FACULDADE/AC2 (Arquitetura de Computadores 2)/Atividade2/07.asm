  start:
  li t0, 0xFFFFFFFF   # t0 = 4294967295 (x)
  li t1, 8192         # t1 = 8192 (y)
  slli t2, t1, 2      # t2 = 4 * y (t2 = 32768)
  sub t3, t0, t2      # t3 = x - 4 * y (t3 = 4294967295 - 32768 = 4294934527)
  nop