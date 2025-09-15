from socket import socket, AF_INET, SOCK_STREAM

server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as client :
    with socket(AF_INET, SOCK_STREAM) as client2:
        with socket(AF_INET, SOCK_STREAM) as client3:
            client.connect(server_addr)
            client2.connect(server_addr)
            client3.connect(server_addr)
    
    
            client.sendall("Hello server".encode())
            client2.sendall("2".encode())
    
            data = client.recv(100)
            print("Kaptam:",data.decode())
            client.close()