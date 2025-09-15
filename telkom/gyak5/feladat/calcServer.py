from socket import socket, AF_INET, SOCK_DGRAM
import struct

packer = struct.Struct('I I 1s')
packer1 = struct.Struct('I')

server_addr = ('',10000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_addr)
    data, client_addr = server.recvfrom(packer.size)
    print("Kaptam:",data)
    unp_data = packer.unpack(data)
    print("Unpack:",unp_data)
    x = eval(str(unp_data[0])+unp_data[2].decode()+str(unp_data[1]))
    
    send_data = packer1.pack(x)
    
    server.sendto(send_data,client_addr)
