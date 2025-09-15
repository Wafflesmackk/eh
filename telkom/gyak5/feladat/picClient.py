from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_DGRAM) as client, open("netOut.jpg","rb") as f:
    data = f.read(200)
    while data:
        client.sendto(data,server_addr)
        res,_ = client.recvfrom(2)
        print("Kaptam: ", res.decode())
        data = f.read(200)
    client.sendto("".encode(), server_addr)
    data,_ = client.recvfrom(2)