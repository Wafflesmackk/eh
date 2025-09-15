from socket import socket, AF_INET, SOCK_STREAM,timeout
from select import select
import struct, sys
from input_timeout import readInput

server_addr = ('localhost',10000)
packer = struct.Struct('10s 100s')

username = sys.argv[1]

def prompt(n1):
    if n1:
        print("")
    print("<"+username+">", end="")    
    sys.stdout.flush()

with socket(AF_INET, SOCK_STREAM) as client:
    client.settimeout(1.0)
    client.connect(server_addr)
    
    data = packer.pack(b'NAME',username.encode())
    client.sendall(data)
    prompt(True)
    
    while True:
        try:
            data = client.recv(200)
            print(data.decode())
            sys.stdout.flush()
        except timeout:
            pass
        
        try:
            msg = readInput()
            msg = msg.strip()
            if(msg != "") :
                data = packer.pack(b'MSG',msg.encode())
                client.sendall(data)
                prompt(True)
        except timeout:
            pass