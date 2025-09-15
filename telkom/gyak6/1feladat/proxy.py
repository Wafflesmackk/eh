from socket import AF_INET, socket, SOCK_STREAM, SOCK_DGRAM
from select import select
import struct

proxy_addr = ('localhost',10000)
server_addr = ('localhost',11000)

packer = struct.Struct('I I 1s')
packer1 = struct.Struct('I')


with socket(AF_INET,SOCK_STREAM) as proxy, socket(AF_INET,SOCK_DGRAM) as udp_client:
    inputs = [proxy]
    proxy.bind(proxy_addr)
    proxy.listen(1)
    while True:
        r,w,e = select(inputs,inputs,inputs)
        if not (r or w or e):
            continue	
        for s in r:
            if s is proxy:
                client, client_addr = s.accept()
                print("Csatlakozott",client_addr)
                inputs.append(client)
            else:
                data = s.recv(packer.size)
                if not data:
                    inputs.remove(s)
                    print("Kilepett")
                    s.close()
                else:
                    udp_client.sendto(data, server_addr)
                    resp, _ = udp_client.recvfrom(200)
                    s.sendall(resp)