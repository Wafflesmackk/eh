from socket import socket, AF_INET, SOCK_DGRAM, SOCK_STREAM
from struct import Struct

packer = Struct('15si')

server_address = ('localhost',10000)
server2_address = ()

with socket(AF_INET, SOCK_DGRAM) as client:
    client.sendto("GET".encode(),server_address)
    data,_ = client.recvfrom(packer.size)
    print("kaptam",data)
    d = packer.unpack(data)
    server2_address = (d[0].decode().strip("\0"),d[1])

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server2_address)
    client.sendall("Hello New Server".encode())
    data = client.recv(200)
    print("Uzente:",data.decode())