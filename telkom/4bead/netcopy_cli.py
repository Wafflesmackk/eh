import hashlib
from socket import socket, AF_INET, SOCK_STREAM
import sys

def md5calc(file_path):
    md5 = hashlib.md5()
    with open(file_path, "rb") as file:
        while True:
            data = file.read(4096)
            if not data:
                break
            md5.update(data)
    return md5.hexdigest()


if len(sys.argv) != 7:
        print("Hasznalat: python3 netcopy_cli.py <srv_ip> <srv_port> <chsum_srv_ip> <chsum_srv_port> <file_id> <file_path>")
        sys.exit(1)
        

server_addr = (sys.argv[1], int(sys.argv[2]))
chsum_srv_ip = sys.argv[3]
chsum_srv_port = int(sys.argv[4])

file_id = int(sys.argv[5])
file_path = sys.argv[6]

client = socket(AF_INET, SOCK_STREAM)

client.connect(server_addr)
client.settimeout(1.0)

with open(file_path, "rb") as file:
        while True:
            data = file.read(4096)
            if not data:
                break
            client.send(data)

client.close()

chsum_client = socket(AF_INET, SOCK_STREAM)
chsum_client.connect((chsum_srv_ip, chsum_srv_port))

md5_checksum = md5calc(file_path)
message = f"BE|{file_id}|60|{len(md5_checksum)}|{md5_checksum}"
chsum_client.send(message.encode())

response = chsum_client.recv(4096).decode()
print(f"Checksum szerver válasza: {response}")

chsum_client.close()
