from socket import socket, AF_INET, SOCK_STREAM
import sys
from select import select

server_addr = ('',10000)
countingTo = int(sys.argv[1])
count = 0
if countingTo < 1 or countingTo > 5:
    print('incorrect count so it is set to 5')
    countintTo = 5

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    inputs = [ server ]
    while True:
        ''' print ('count',count)
        client, client_addr = server.accept()
        print("Csatlakozott: ", client_addr)
        data = client.recv(200)
        print('diakok:', data.decode())
        if data.decode() == 'Kerek feladatot':
            count += 1
        if count == countingTo :
            count = 0
            client.sendall(b'Tessek a feladat!')
            data = client.recv(200)
            print(data)
            if data.decode() == 'Koszonjuk':
                client.sendall(b'Szivesen')

        else:
            client.sendall(b'Meg nincs')
            client.close()'''
            
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
                data = s.recv(200)
                if not data:
                    inputs.remove(s)
                    s.close()
                    print("Kilepett")
                else:
                    print('diakok:', data.decode())
                    if data.decode() == 'Kerek feladatot':
                        count += 1
                    if count == countingTo :
                        count = 0
                        client.sendall(b'Tessek a feladat!')
                        data = client.recv(200)
                        print(data)
                        if data.decode() == 'Koszonjuk':
                            client.sendall(b'Szivesen')
                    else:
                        client.sendall(b'Meg nincs')