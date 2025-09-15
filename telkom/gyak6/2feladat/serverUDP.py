from socket import socket, AF_INET, SOCK_DGRAM
import struct

packer = struct.Struct('15s i')

server_address = ('',10000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_address)

    data, client_addr = server.recvfrom(200)
    print("Kaptam:",data.decode(),"tole:",client_addr)
    
    data = packer.pack("localhost".encode(),11000)
    server.sendto(data,client_addr)