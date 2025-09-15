from socket import socket, AF_INET, SOCK_STREAM,timeout
import struct
import sys
import random


packer = struct.Struct('ci')

if len(sys.argv) != 3:
        print("Incorect fromat, should be: python3 client.py <hostname> <port num>")
        exit(1)

hostname = sys.argv[1]
port = int(sys.argv[2])
msg = ''
operator = ''
tipp = 50
min = 1
max = 100

server_addr = (hostname,port)
with socket(AF_INET, SOCK_STREAM) as client:
    client.settimeout(1.0)
    client.connect(server_addr)
    
    
    while True:
        try:
            data = client.recv(packer.size)
            usable_data = data.decode()
            msg = usable_data[0]
            number = usable_data[1]
        except timeout:
            pass
        try:
            if msg == 'V' or msg == 'Y' or msg == 'K':
                break     
            
            if (msg == 'I' and operator == b'<'):
                max = tipp - 1
            elif (msg == 'I' and operator == b'>'):
                min = tipp + 1
            elif(msg == 'N' and operator == b'>'):
                max = tipp
            elif(msg == 'N' and operator == b'<'):
                min = tipp
            
            tipp = (min + max) // 2
            
            if min == max :
                data = packer.pack(b'=',min)
                client.sendall(data)
                ##print(min, 'final tipp')
            else:
                operator = random.choice([b'>', b'<'])
                data = packer.pack(operator,tipp)
                ##print('tipp:',tipp, 'operator:', operator)
                client.sendall(data)
        except timeout:
            pass