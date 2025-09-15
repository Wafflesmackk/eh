from socket import socket, AF_INET, SOCK_STREAM

server_addr = ('oktnb147.inf.elte.hu',10000)

with socket(AF_INET, SOCK_STREAM) as client :
    client.connect(server_addr)
    client.sendall("hmmmmmmmm".encode())
    
    data = client.recv(100)
    print("Kaptam:",data.decode())
    client.close()