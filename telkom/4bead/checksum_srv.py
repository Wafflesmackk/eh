from socket import socket, AF_INET, SOCK_STREAM
from select import select
import sys
import threading
import time

checksums = []
lock = threading.Lock()


def insert(fileId, validity, lengthInByte, bytes ):
    expTime = int(time.time()) + int(validity)
    with lock:
        tmpChecksum = (fileId,lengthInByte, bytes, expTime )
        checksums.append(tmpChecksum)

def get(fileId):
    for i in range(len(checksums)):
        if int(time.time()) > checksums[i][3] :
            del checksums[i]
        if checksums[i][0] == fileId:
            return str(checksums[i][1]) + '|' + str(checksums[i][2])
    return "0|"
'''
print(checksums)
insert(1237671, 60, 12, 'abcdefabcdef')
print(checksums)
insert(1237672,5,12, 'abcdefabcdef')
print(checksums)
insert(1237673,15,12, 'abcdefabcdef')

print(get(1237673))
print(get(1))

time.sleep(7)
print(int(time.time()))
print(get(1237673))
print(checksums)
'''
if len(sys.argv) != 3:
        print("Hasznalat: python3 checksum_srv.py < ip > < port >")
        sys.exit(1)

server_addr = (sys.argv[1], int(sys.argv[2]))

with socket(AF_INET,SOCK_STREAM) as server:
    inputs = [server]
	
    server.bind(server_addr)
    server.listen(1)
	
    while True:
        timeout = 1
        r, w, e = select(inputs,inputs,inputs,timeout)
        if not (r or w or e):
            continue
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                inputs.append(client)
                #print("Kapcsolodott: ",client_addr)
            else:
                data = s.recv(1024)
                if not data:
                    #print("Kilepett",s)
                    inputs.remove(s)
                    s.close()
                else:
                    print("Kaptam:",data.decode())
                    if data.startswith(b"BE|"):
                        parts = data.split(b'|')
                        insert(parts[1],parts[2],parts[3],parts[4])
                        s.sendall("OK".encode())
                    elif data.startswith(b"KI|"):
                        parts = data.split(b'|')
                        s.sendall(get(parts[1]).encode())
                        #print("kuldtem:"+ str( get(parts[1])))