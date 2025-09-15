from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('',10000)

with socket(AF_INET, SOCK_DGRAM) as server, open("out.jpg","wb") as f:
    server.bind(server_addr)
    while True:
        data, client_addr = server.recvfrom(200)
        server.sendto("Ok".encode(),client_addr)
        
        if data != b"":
            f.write(data)
        else:
            break    