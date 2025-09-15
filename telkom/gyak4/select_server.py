from socket import socket, AF_INET, SOCK_STREAM
from select import select



server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    
    inputs = [ server ]
    while True:
        timeout = 1
        r, w, e = select(inputs,inputs,inputs,timeout)
        
        if not(r or w or e):
            continue
        
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                print("Csatlakozott: ", client_addr)
            else:
                data = s.recv(200)
                if not data:
                    inputs.remove(s)
                    s.close()
                    print("Kilepett")
                else:
                    print("Kaptam: ", data.decode())
                    s.sendall("Hello kliens".encode())    