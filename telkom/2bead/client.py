import sys
import struct

packer1 = struct.Struct('? c 9s')
packer2 = struct.Struct('9s i f')
packer3 = struct.Struct('f c ?')
packer4 = struct.Struct('9s ? i')

packers = []
packers.append(packer1)
packers.append(packer2)
packers.append(packer3)
packers.append(packer4)

#1 feladat
for i in range (1,5) :
    with open(sys.argv[i], 'rb')  as f:
        packer = packers[i-1] 
        data = f.read(packer.size)
        usable_data = packer.unpack(data)
        print(usable_data)

#2 feladat
packer5 = struct.Struct('13s i ?')
packer6 = struct.Struct('f ? c')
packer7 = struct.Struct('i 11s f')
packer8 = struct.Struct('c i 14s')

data =[]
data.append(packer5.pack("elso".encode(), 53, True))
data.append(packer6.pack(56.5, False, 'X'.encode()))
data.append(packer7.pack(44, "masodik".encode(), 63.9))
data.append(packer8.pack('Z'.encode(), 75, "harmadik".encode()))



for i in range (0,4) :
    print (data[i])