from socket import socket, AF_INET, SOCK_STREAM, SOCK_DGRAM
import struct

server_address = ('localhost', 11000)

with socket(AF_INET,SOCK_DGRAM) as server:
	server.bind(server_address)

	packer = struct.Struct('iic')

	while True:
		data, client = server.recvfrom(packer.size)

		unpacked_data = packer.unpack(data)
		print ('Kaptam: ', unpacked_data)
		x = eval(str(unpacked_data[0])+str(unpacked_data[2].decode())+str(unpacked_data[1]))
		
		server.sendto(str(x).encode(), client)