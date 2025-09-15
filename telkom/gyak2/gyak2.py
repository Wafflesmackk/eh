import struct
import socket

import sys

packer = struct.Struct('20s i')


if len(sys.argv) == 1:
    print(socket.gethostname())
else:
    with open('C:/telkom/gyak2/domainPort.bin','rb') as f:
        #data = f.read(packer.size)
        #while data:
            #print(data)
            #print(packer.unpack(data))
            #usable_data = packer.unpack(data)
            #data = f.read(packer.size)   
        f.seek((int(sys.argv[2]) - 1 )  * packer.size)
        data = f.read(packer.size)
        usable_data = packer.unpack(data)
        
        if (sys.argv[1] == 'domain'):
            domainName = usable_data[0].decode().strip("\x00")
            print(domainName)
            print(socket.gethostbyname(domainName))
        elif(sys.argv[1] == 'port'):
            portNum = usable_data[1]
            print(portNum,socket.getservbyport(portNum))
        else:
            print('invalid option')    
