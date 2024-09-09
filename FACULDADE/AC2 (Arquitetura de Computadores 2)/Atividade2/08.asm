start:
    ori x8, x0, 0x01        # x8 = 0x01

    slli x8, x8, 1          # x8 = x8 << 1 (x8 = 0x02)
    ori x8, x8, 0x01        # x8 = x8 | 0x01 (x8 = 0x03)

    slli x8, x8, 2          # x8 = x8 << 2 (x8 = 0x0C)
    ori x8, x8, 0x03        # x8 = x8 | 0x03 (x8 = 0x0F)

    slli x8, x8, 4          # x8 = x8 << 4 (x8 = 0xF0)
    ori x8, x8, 0x0F        # x8 = x8 | 0x0F (x8 = 0xFF)

    slli x8, x8, 8          # x8 = x8 << 8 (x8 = 0xFFFF00)
    ori x8, x8, 0xFF        # x8 = x8 | 0xFF (x8 = 0xFFFFFF)
    
    mv t0, x8               # t0 = x8 (t0 = 0xFFFFFFFF)

    slli x8, x8, 16         # x8 = x8 << 16 (x8 = 0xFFFFFF00)
    
    add t1,x8,t0            # t1 = x8 + t0 (t1 = 0xFFFFFFFF)

    nop                     