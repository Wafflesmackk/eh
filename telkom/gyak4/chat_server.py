from socket import socket, AF_INET, SOCK_STREAM
from select import select
import queue
import struct

server_addr = ('localhost',10000)
packer = struct.Struct('10s 100s')

msg_q  = queue.Queue()
username = {}

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(5)
    inputs = [ server ]
    
    while True:
        timeout = 1
        r, w, e = select(inputs,inputs,inputs, timeout)
        if not (r or w or e):
            continue
        
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                print("Csatlakozottt", client_addr)
            else:
                data = s.recv(packer.size)
                if not data:
                    msg_q.put((s,"logout "+username[s]))
                    inputs.remove(s)
                    print("Kilepett", username[s])
                    del username[s]
                    s.close()
                else:
                    msgtyp, msgvalue = packer.unpack(data)
                    if msgtyp.strip(b'\x00') == b'NAME':
                        username[s] = msgvalue.decode().strip('\x00')
                        msg_q.put((s,"login "+username[s]))
                    elif msgtyp.strip(b'\x00') == b'MSG':   
                        msg_q.put((s, msgvalue.decode().strip('\x00')))

        while not msg_q.empty():
            next_msg = msg_q.get()
            print("msg", next_msg)
            for s in w:
                if next_msg[0] != s:
                    if next_msg[0] in username:
                        s.sendall(("["+username[next_msg[0]]+"]: "+next_msg[1]).encode())
                    else:
                        s.sendall(next_msg[1].encode())