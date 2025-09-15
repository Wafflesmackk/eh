import json

##1 feladat
def szokoev(ev):
    if (ev % 400 == 0):
        print(str(ev) + " az szokoev")
    elif (ev % 4 == 0  and ev % 100 != 0):
            print(str(ev) + " az szokoev")
    else:
        print(str(ev) + " az nem szokoev") 


f = open('C:/telkom/gyak1/gyak1.json')
data = json.load(f)

for i in data['evek']:
    szokoev(i['ev'])

f.close()




##2 feladat
def pont_calc(haziPont, zhPont, maxMininet, minMininet):
    ponthatarok = [35,39,42,47]
    for i in range(4):
        pontReq = ponthatarok[i] - (haziPont + zhPont)
        if(pontReq < minMininet):
            print(str(i + 2 ) + " : " + str(minMininet))
        elif(pontReq > maxMininet):
            print(str(i + 2) + " : " + "Remenytelen")
        else:
            print(str(i + 2 ) + " : " + str(pontReq))
            

f = open('C:/telkom/gyak1/gyak1pontok.json')
data = json.load(f)

pont_calc(data['HaziPont']['elert'],data['ZHPont']['elert'],data['mininetPont']['max'],8)

f.close()