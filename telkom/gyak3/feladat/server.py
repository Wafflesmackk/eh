from socket import socket, AF_INET, SOCK_STREAM
from select import select

import struct

server_addr = ('',10000)

packer = struct.Struct('i i 1s')
packer2 = struct.Struct('i')

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    inputs = [ server ]
    
    while True:
        '''
        client, client_addr = server.accept()  #gyak3
        print("Csatlakozott: ", client_addr)
        
        data = client.recv(packer.size)
        szam1, szam2, op = packer.unpack(data)
        
        x = eval(str(szam1) + op.decode() + str(szam2))
        res = packer2.pack(x)
        client.sendall(res)'''
        
        timeout = 1  #gyak4
        r, w, e = select(inputs,inputs,inputs,timeout)
        
        if not(r or w or e):
            continue
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                print("Csatlakozott: ", client_addr)
            else:
                data = s.recv(packer.size)
                if not data:
                    inputs.remove(s)
                    s.close()
                    print("Kilepett")
                else:
                    szam1, szam2, op = packer.unpack(data)
                    x = eval(str(szam1) + op.decode() + str(szam2))
                    res = packer2.pack(x)
                    client.sendall(res)    