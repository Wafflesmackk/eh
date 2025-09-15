from socket import socket, AF_INET, SOCK_STREAM
from select import select
import struct
import time

packer = struct.Struct('1c i')


server_addr = ('localhost',10000)

x = 0
y = 0




occupied = False

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    inputs = [ server ]

    
    while True:
        '''client, client_addr = server.accept()
        if(not occupied):
            print("Csatlakozott nem foglalt: ", client_addr)
            client.sendall(packer.pack(b'S',1))
        else:
            print("Csatlakozott foglalt: ", client_addr)
            client.sendall(packer.pack(b'S',1))
        while True:
            data = client.recv(packer.size)
            print("Kaptam: ", data.decode())
            client.sendall(packer.pack(b'A',1))'''
            
        timeout = 1
        r, w, e = select(inputs,inputs,inputs,timeout)
        
        if not(r or w or e):
            continue
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                ##print("Csatlakozott: ", client_addr)
                if(not occupied):
                    print("Csatlakozott nem foglalt: ", client_addr)
                    client.sendall(packer.pack(b'S',1))
                    occupied = True
                else:
                    print("Csatlakozott foglalt: ", client_addr)
                    client.sendall(packer.pack(b'S',1))
                    print("Kilepett")
            else:
                data = s.recv(packer.size)
                if not data:
                    inputs.remove(s)
                    s.close()
                    if len(inputs) == 1:
                        occupied = False
                    print(inputs)
                    print("Kilepett")
                else:
                    print("pos",x,y)
                    print("Kaptam: ", packer.unpack(data))
                    command = packer.unpack(data)
                    if command[0] == b'F':
                            x += command[1]
                            s.sendall(packer.pack(b'A',0)) 
                    elif command[0] == b'B':
                            x -= command[1]
                            s.sendall(packer.pack(b'A',0)) 
                    elif command[0] == b'L':
                            y -= command[1]
                            s.sendall(packer.pack(b'A',0))
                    elif command[0] == b'R':
                            y+= command[1]
                    elif(command[0] == b'P' and command[1] == 0):
                        s.sendall(packer.pack(str(x).encode('utf-8'),y))
                    else:
                        s.sendall(packer.pack(b'E',1))
                    time.sleep(1)