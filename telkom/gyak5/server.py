from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('',10000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_addr)
    
    data, client_addr = server.recvfrom(200)
    print("Kaptam:",data.decode(),"tole:",client_addr)
	
    server.sendto("Hello kliens!".encode(),client_addr)