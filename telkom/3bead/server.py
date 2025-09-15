
from socket import socket, AF_INET, SOCK_STREAM, SHUT_RDWR
import struct
import sys
from select import select
import random

def boolToResponse(inputBool):
    if inputBool:
        return b'I'
    else:
        return b'N'

def isWinner(inputBool):
    if inputBool:
        return b'Y'
    else:
        return b'K' 

guessNum = random.randint(1,100)

##print(guessNum)

packer = struct.Struct('ci')

if len(sys.argv) != 3:
        print("Incorect fromat, should be: python3 client.py <hostname> <port num>")
        exit(1)

hostname = sys.argv[1]
port = int(sys.argv[2])

server_addr = (hostname,port)

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    inputs = [ server ]
    guessed = False
    
    while True:
        timeout = 1
        r, w, e = select(inputs,inputs,inputs, timeout)
        if not (r or w or e):
            continue
        
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                ##print("Csatlakozott", client_addr)
            else:
                try: 
                    data = s.recv(packer.size)
                except:
                    continue
                if not data:
                    inputs.remove(s)
                    ##print("Kilepett", client_addr)
                    s.close()
                else:
                    if not guessed:
                        operator, num =  packer.unpack(data)
                        print('operator:', operator, 'num', num)
                        response = ''
                        if operator.strip(b'\x00') == b'<':
                            response = boolToResponse(guessNum < num)
                        
                        elif operator.strip(b'\x00') == b'>':
                            response = boolToResponse(guessNum > num)
                        
                        elif operator.strip(b'\x00') == b'=': 
                            response = isWinner(guessNum == num)
                            if response == b'Y':
                                guessed = True
                        send_data = packer.pack(response,123)
                        ##print(response)
                        s.send(send_data)
        if guessed :
            for s in w:
                try: 
                    response = b'V'
                    send_data = packer.pack(response,123)
                    s.sendall(send_data)
                    inputs.remove(s)
                    ##print("Kilepett", client_addr)
                    s.close()    
                except:
                    break