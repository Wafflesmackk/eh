import hashlib
from select import select
from socket import socket, AF_INET, SOCK_STREAM
import sys

def md5calc(client, file_path,data):
    md5 = hashlib.md5()
    with open(file_path, "wb") as file:
            if  data:
                file.write(data)
                md5.update(data)
    return md5.hexdigest()

if len(sys.argv) != 7:
        print("Használat: python3 netcopy_srv.py <srv_ip> <srv_port> <chsum_srv_ip> <chsum_srv_port> <file_id> <file_path>")
        sys.exit(1)


srv_ip = sys.argv[1]
srv_ip = srv_ip.replace(" ", "")
srv_port = int(sys.argv[2])

server_addr = (srv_ip, srv_port)

chsum_srv_ip = sys.argv[3]
chsum_srv_ip = chsum_srv_ip.replace(" ", "")
chsum_srv_port = int(sys.argv[4])

file_id = int(sys.argv[5])
file_path = sys.argv[6]


with socket(AF_INET,SOCK_STREAM) as server:
    inputs = [server]
	
    server.bind(server_addr)
    server.listen(5)
    
    while True:

        timeout = 1
        r, w, e = select(inputs,inputs,inputs,timeout)
        if not (r or w or e):
            continue
        for s in r:
            if s is server:
                client, client_addr = s.accept()
                if(client):
                    chsum_client = socket()
                    chsum_client.connect((chsum_srv_ip, chsum_srv_port)) 
                inputs.append(client)
                #print("Kapcsolodott: ",client_addr)
            else:
                data = s.recv(1024)
                if not data:
                    #print("Kilepett",s)
                    inputs.remove(s)
                    s.close()
                else:
                    #print("Kaptam:",data.decode())
                    
                    msg = f"KI|{file_id}"
                    chsum_client.send(msg.encode())
                    
                    md5 = md5calc(client,file_path,data)
                    #print(md5)
                    
                    resp = chsum_client.recv(4096).decode()
                    chsum_client.close()
                    #print(resp)
                    #print(f"'b'{len(md5)}|'b'{md5}")
                    
                    if resp.startswith("b'0|'") or resp != f"b'{len(md5)}'|b'{md5}'":
                        print("CSUM CORRUPTED")
                    else:
                        print("CSUM OK")