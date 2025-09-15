from socket import socket, AF_INET, SOCK_STREAM
import struct

server_addr = ('localhost', 10000)

packer = struct.Struct('iic')

with socket(AF_INET,SOCK_STREAM) as client:
	a = input("Kerek egy szamot:")
	b = input("Kerek egy operatort:")
	c = input("Kerek egy szamot:")

	packed_data = packer.pack(int(a), int(c), b.encode())
	client.connect(server_addr)

	client.sendall(packed_data)
	data = client.recv(200)
	print("Eredmeny:",data.decode())