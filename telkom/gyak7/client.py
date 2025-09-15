from socket import socket, AF_INET, SOCK_STREAM

server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as client :
    client.connect(server_addr)
    client.sendall(b'Kerek feladatot')
    
    res = client.recv(200)
    print('server:',res) 
    if(res == b'Tessek a feladat!'):
        client.sendall(b'Koszonjuk')
        res = client.recv(200)
        print('server:',res.decode())