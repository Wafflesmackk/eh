from socket import socket, AF_INET, SOCK_STREAM

import struct

packer = struct.Struct('i i 1s')
packer2 = struct.Struct('i')

szam1 = input("Kerek egy szamot: ")
op = input("Kerek egy muveletet ")
szam2 = input("Kerek egy szamot: ")

data = packer.pack(int(szam1), int(szam2), op.encode())



server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as client :
    client.connect(server_addr)
    client.sendall(data)
    
    res = client.recv(packer2.size)
    unpRes = packer2.unpack(res)
    print("Eredmeny:",unpRes[0])