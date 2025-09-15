from socket import socket, AF_INET, SOCK_STREAM
import struct

packer = struct.Struct('1c i')

server_addr = ('localhost',10000)


with socket(AF_INET, SOCK_STREAM) as client :
    client.connect(server_addr)
    data = client.recv(packer.size)
    usable_data = packer.unpack(data)
    print(usable_data)
    if(usable_data == (b'S',2)):
        client.close()
    elif(usable_data == (b'S',1)):
        
        client.sendall(packer.pack(b'F',1))
        print("sent command F")
        data = client.recv(packer.size)
        
        client.sendall(packer.pack(b'P',0))
        data = client.recv(packer.size)
        
        
        usable_data = packer.unpack(data)
        print('Pozicio a commandok utan:',usable_data)
        
        client.sendall(packer.pack(b'C',2))
        print("sent command")
        data = client.recv(packer.size)
        
        client.sendall(packer.pack(b'P',0))
        
        data = client.recv(packer.size)
        usable_data = packer.unpack(data)
        print('Pozicio a commandok utan:',usable_data)
        
        client.sendall(packer.pack(b'L',5))
        print("sent command")
        data = client.recv(packer.size)
        
        
        client.sendall(packer.pack(b'P',0))
        
        data = client.recv(packer.size)
        usable_data = packer.unpack(data)
        print('Pozicio a commandok utan:',usable_data)
        
        client.sendall(packer.pack(b'B',3))
        data = client.recv(packer.size)
    
        client.sendall(packer.pack(b'P',0))
        
        data = client.recv(packer.size)
        usable_data = packer.unpack(data)
        print('Pozicio a commandok utan:',usable_data)
        
        
        client.sendall(packer.pack(b'R',1))
        data = client.recv(packer.size)

        
        client.sendall(packer.pack(b'P',0))
        data = client.recv(packer.size)
        
        usable_data = packer.unpack(data)
        print('Pozicio a commandok utan:',usable_data)
        client.close