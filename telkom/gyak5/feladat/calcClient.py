from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('localhost',10000)


import struct

packer = struct.Struct('I I 1s')
packer1 = struct.Struct('I')


with socket(AF_INET, SOCK_DGRAM) as client:
    client.connect(server_addr)
    szam1 = input("Adj meg egy szamot:")
    op = input("Adj meg egy operatort:")
    szam2 = input("Adj meg masik szamot:")
	
    values = (int(szam1), int(szam2), op.encode())
    packed_data = packer.pack(*values)
    client.sendto(packed_data,server_addr)
    
    data, _ = client.recvfrom(packer1.size)
    unp_data = packer1.unpack(data)
    print("Kaptam:", unp_data )
