from socket import socket, AF_INET, SOCK_STREAM

server_addr = ('',10000)

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(3)
    
    while True:
        client, client_addr = server.accept()
        print("Csatlakozott: ", client_addr)
    
        data = client.recv(100)
        print("Kaptam: ", data.decode())
    
        #client.sendall("Hello kliens!".encode())
        client.close()