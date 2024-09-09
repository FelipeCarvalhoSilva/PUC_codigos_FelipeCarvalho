start:
    li x8, 0x12345678    # Carrega 0x12345678 em x8

    # Extrair o byte mais significativo (0x12)
    slli x9, x8, 0      # Desloca x8 24 bits à esquerda (x9 = 0x12340000)
    srli x9, x9, 24      # Desloca x9 24 bits à direita (x9 = 0x12)

    # Extrair o terceiro byte (0x56)
    slli x11, x8, 16
    srli x11, x11, 24   

    # Extrair o segundo byte (0x34)
    slli x10, x8, 8      
    srli x10, x10, 24   

    # Extrair o byte menos significativo (0x78)
    andi x12, x8, 0xFF  

    nop                 
