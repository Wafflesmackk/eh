import json

f = open('cs1.json')
data = json.load(f)

duration = data['simulation']['duration'] + 1

demands = data['simulation']['demands']

routes = data['possible-circuits']

links = data['links']


def findRoute(begin, end):
    possibleRoutes = []
    for route in routes :
        if route[0] == begin and route[len(route) - 1] == end :
            possibleRoutes.append(route)
    return possibleRoutes;

#print (findRoute('A', 'C')) 

def canReserve(occupiedRoutes, reserveing,reserved) :
    if reserved == True:
        return False
    
    for route in occupiedRoutes :
        length = len(route)
        for i in range(1, length - 1):
            if route[i] in reserveing :
                return False
    occupiedRoutes.append(reserveing)
    return True

def isIdenticalRoute(this, other):
    return this['start-time'] == other['start-time'] and this['end-points'] == other['end-points'] and this['end-time'] == other['end-time'] and this['demand'] == other['demand']


index = 0
occupiedRoutes = []
liveDemands = []
liveRoute = []
for currTime in range (1, duration) :
    for demand in demands :
        if demand['start-time'] == currTime:
            index = index + 1
            reserved = False
            for route in findRoute(demand['end-points'][0],demand['end-points'][1]):
                if canReserve(occupiedRoutes,route,reserved):
                    reserved = True
            if reserved:
                live = demand 
                live.update({'route': occupiedRoutes[-1]})
                liveDemands.append(live)
                print(str(index)+".","igény foglalás:",str(demand['end-points'][0]) + "<->" + str(demand['end-points'][1]),"st:" + str(currTime),"– sikeres")
            else:
                print(str(index)+".","igény foglalás:",str(demand['end-points'][0]) + "<->" + str(demand['end-points'][1]),"st:"+str(currTime),"– sikertelen")
        elif demand["end-time"] == currTime:
            index = index + 1
            removeElement = []
            for living in liveDemands:
                if isIdenticalRoute(living,demand):
                    removeElement = living
                    occupiedRoutes.remove(removeElement['route'])
            if removeElement != [] :
                liveDemands.remove(removeElement)
                print(str(index)+".","igény felszabadítás:",str(demand['end-points'][0]) + "<->" + str(demand['end-points'][1]),"st:"+str(currTime))
