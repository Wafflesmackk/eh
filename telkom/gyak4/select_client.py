from socket import socket, AF_INET, SOCK_STREAM
from select import select
import time



server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    
    for i in range(5):
        client.sendall("Hello server".encode())
        data = client.recv(200).decode()
        print("Kaptam valaszt: ", data)
        time.sleep(1.0)