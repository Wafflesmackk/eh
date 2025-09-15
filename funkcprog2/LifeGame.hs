--Daka Bence I5RQFI
module LifeGame where
    import Data.List
    import Data.Maybe

    type Coordinate = (Integer, Integer)
    type Generation = [Coordinate]

    single :: Generation
    single = [ (42, 42) ]

    single2 :: Generation
    single2 = [ (93, 16) ]

    block :: Generation
    block = [ (5, 6), (5, 7), (6, 6), (6, 7) ]

    row :: Generation
    row = [ (10, 1), (10, 2), (10, 3) ]

    column :: Generation
    column = [ (9,  2), (10, 2), (11, 2) ]

    caterer :: Generation
    caterer = [ (2, 4), (3, 2), (3, 6), (3, 7), (3, 8), (3, 9), (4, 2), (4, 6), (5, 2), (6, 5), (7, 3), (7, 4) ]

    o15 :: Generation
    o15 = [(0,1),(1,1),(2,0),(2,2),(3,1),(4,1),(5,1),(6,1),(7,0),(7,2),(8,1),(9,1)]

    o312 :: Generation
    o312 = [(1,21),(1,22),(2,21),(2,22),(6,32),(6,33),(7,23),(7,24),(7,31),(7,34),(8,23),(8,32),(8,33),(9,7),(9,23),(10,6),(10,8),(10,24),(11,6),(11,8),(12,7),(19,33),(19,36),(20,34),(20,35),(20,36),(21,1),(21,2),(21,41),(21,42),(22,1),(22,2),(22,41),(22,42),(23,7),(23,8),(23,9),(24,7),(24,10),(31,36),(32,35),(32,37),(33,19),(33,35),(33,37),(34,20),(34,36),(35,10),(35,11),(35,20),(36,9),(36,12),(36,19),(36,20),(37,10),(37,11),(41,21),(41,22),(42,21),(42,22)]

    glider :: Generation
    glider = [(6,5), (7,3), (7,5), (8,4), (8,5)]

    lwss :: Generation
    lwss = [(0,1),(0,2),(0,3),(0,7),(0,8),(0,9),(1,0),(1,3),(1,7),(1,10),(2,3),(2,7),(3,3),(3,7),(3,11),(4,0),(4,2),(4,7),(5,8),(5,10)]

    five6P6H1V0 :: Generation
    five6P6H1V0 = [(1,6),(1,7),(1,8),(1,19),(1,20),(1,21),(2,1),(2,2),(2,3),(2,5),(2,13),(2,14),(2,22),(2,24),(2,25),(2,26),(3,5),(3,9),(3,12),(3,15),(3,18),(3,22),(4,5),(4,11),(4,16),(4,22),(5,11),(5,12),(5,15),(5,16),(6,8),(6,12),(6,15),(6,19),(7,8),(7,10),(7,17),(7,19),(8,9),(8,10),(8,11),(8,12),(8,13),(8,14),(8,15),(8,16),(8,17),(8,18),(9,11),(9,16),(10,9),(10,18),(11,8),(11,19),(12,9),(12,18)]

    one19P4H1V0 :: Generation
    one19P4H1V0 = [(1,34),(2,17),(2,33),(2,35),(3,7),(3,9),(3,16),(3,22),(3,23),(3,32),(4,7),(4,12),(4,17),(4,19),(4,20),(4,21),(4,22),(4,23),(4,24),(4,29),(4,30),(5,7),(5,9),(5,10),(5,11),(5,12),(5,13),(5,14),(5,15),(5,16),(5,27),(5,30),(5,32),(5,33),(5,34),(6,10),(6,16),(6,24),(6,25),(6,26),(6,27),(6,32),(6,33),(6,34),(7,5),(7,6),(7,24),(7,25),(7,26),(7,28),(8,2),(8,5),(8,6),(8,14),(8,15),(8,24),(8,25),(9,2),(9,5),(10,1),(11,2),(11,5),(12,2),(12,5),(12,6),(12,14),(12,15),(12,24),(12,25),(13,5),(13,6),(13,24),(13,25),(13,26),(13,28),(14,10),(14,16),(14,24),(14,25),(14,26),(14,27),(14,32),(14,33),(14,34),(15,7),(15,9),(15,10),(15,11),(15,12),(15,13),(15,14),(15,15),(15,16),(15,27),(15,30),(15,32),(15,33),(15,34),(16,7),(16,12),(16,17),(16,19),(16,20),(16,21),(16,22),(16,23),(16,24),(16,29),(16,30),(17,7),(17,9),(17,16),(17,22),(17,23),(17,32),(18,17),(18,33),(18,35),(19,34)]

    corderShip :: Generation
    corderShip = [(1,33),(1,34),(1,36),(2,32),(2,33),(2,34),(2,36),(2,43),(2,45),(3,31),(3,36),(3,38),(3,43),(4,32),(4,33),(4,40),(4,42),(4,46),(5,33),(5,37),(5,40),(5,43),(5,44),(6,36),(6,38),(6,39),(6,43),(7,35),(7,37),(7,54),(7,55),(8,35),(8,37),(8,54),(8,55),(15,62),(15,63),(16,53),(16,54),(16,62),(16,63),(17,40),(17,50),(17,52),(17,53),(17,54),(17,55),(18,35),(18,39),(18,40),(18,41),(18,42),(18,43),(18,49),(18,50),(18,52),(18,56),(18,57),(19,34),(19,36),(19,44),(19,45),(19,50),(19,53),(19,54),(19,56),(19,57),(20,34),(20,42),(20,44),(20,45),(20,51),(20,52),(20,53),(20,54),(20,55),(20,56),(21,35),(21,44),(21,45),(21,52),(22,36),(22,40),(22,41),(22,42),(22,43),(23,41),(23,42),(23,43),(24,25),(24,27),(24,37),(24,38),(25,25),(25,27),(25,29),(25,36),(25,38),(26,24),(26,27),(26,28),(26,30),(26,35),(26,36),(27,25),(27,26),(27,30),(27,32),(27,34),(27,35),(27,37),(28,25),(28,26),(28,30),(28,31),(28,33),(28,34),(28,35),(28,36),(28,37),(29,29),(29,31),(29,32),(29,36),(29,37),(30,28),(30,30),(31,3),(31,4),(31,6),(32,2),(32,3),(32,4),(32,6),(32,13),(32,15),(33,1),(33,6),(33,8),(33,13),(34,2),(34,3),(34,10),(34,12),(34,16),(35,3),(35,7),(35,10),(35,13),(35,14),(35,26),(36,6),(36,8),(36,9),(36,13),(36,25),(36,26),(36,27),(37,5),(37,7),(37,25),(37,28),(38,5),(38,7),(38,24),(38,29),(39,25),(41,25),(41,28),(42,26),(42,28),(44,22),(45,21),(45,22),(45,23),(46,20),(46,21),(46,23),(46,24),(47,10),(47,19),(47,20),(47,22),(47,28),(48,5),(48,9),(48,10),(48,11),(48,12),(48,13),(48,18),(48,19),(48,26),(48,27),(49,4),(49,6),(49,14),(49,15),(49,18),(49,19),(49,27),(49,28),(50,4),(50,12),(50,14),(50,15),(51,5),(51,14),(51,15),(52,6),(52,10),(52,11),(52,12),(52,13),(53,11),(53,12),(53,13),(57,12),(57,13),(58,12),(58,13)]


    --Alapfeladat

    -- Szomszédok
    neighbors :: Coordinate -> [Coordinate]
    neighbors (a,b) = [(a - 1, b - 1), (a - 1, b), (a-1, b + 1), (a, b - 1), (a, b + 1), (a + 1, b - 1), (a + 1, b), (a + 1, b + 1)]

    --Élő szomszédok
    livingNeighbors :: Generation -> Coordinate -> Int
    livingNeighbors [] _ = 0 
    livingNeighbors (x:xs) a = containsElems (x:xs) (neighbors a)
    
    contains :: Generation -> Coordinate -> Int
    contains [] a = 0
    contains (x:xs) a 
        | x == a = 1
        | otherwise = 0 + contains xs a

    containsElems :: Generation -> [Coordinate] -> Int
    containsElems [] _ = 0
    containsElems _ [] = 0
    containsElems (x:xs) (y:ys) = contains (x:xs) y + containsElems (x:xs) ys

    --Szabályok kiszámítása
    staysAlive :: Generation -> Coordinate -> Bool
    staysAlive [] _ = False
    staysAlive x a = livingNeighbors x a == 2 || livingNeighbors x a == 3

    --Szabályok alkalmazása élő sejtekre
    stepLivingCells :: Generation -> Generation
    stepLivingCells [] = [] 
    stepLivingCells (x:xs) = sort (listHelper (map (staysAlive (x:xs)) (x:xs)) (x:xs))

    listHelper :: [Bool] -> Generation -> Generation 
    listHelper [] _ = []
    listHelper _ [] = []
    listHelper (x:xs) (y:ys)
        | x = y : listHelper xs ys
        | otherwise = listHelper xs ys

    --Élő sejtekkel szomszédos üres cellák
    deadNeighbors :: Generation -> [Coordinate]
    deadNeighbors [] = []
    deadNeighbors (x:xs) =  removeDuplicates ( sort ( doubleListHelper (map neighbors (x:xs)) (x:xs)))

    singleListHelper :: [Coordinate] -> Generation -> [Coordinate] 
    singleListHelper [] _ = []
    singleListHelper  (y:ys) (x:xs) 
        | null ys && contains (x:xs) y == 0 = [y]
        | null ys && contains (x:xs) y == 1 = []
        | contains (x:xs) y == 0 = y : singleListHelper ys (x:xs) 
        | otherwise = singleListHelper ys (x:xs)

    doubleListHelper :: [[Coordinate]] -> Generation -> [Coordinate]
    doubleListHelper [] _ = [] 
    doubleListHelper (x:xs) a = (singleListHelper x a) ++ (doubleListHelper xs a)

    removeDuplicates :: [Coordinate] -> [Coordinate]
    removeDuplicates [] = []
    removeDuplicates [x] = [x]
    removeDuplicates (x:xs)
        | null (head xs) = [x]
        | x == head xs = removeDuplicates xs
        | otherwise = x : removeDuplicates xs

    --Szabály alkalmazása üres cellákra
    willLive :: Generation -> Coordinate -> Bool
    willLive [] _ = False
    willLive x a = livingNeighbors x a == 3 && contains x a == 0

    stepDeadCells :: Generation -> Generation
    stepDeadCells [] = []
    stepDeadCells (x:xs) = sort( listHelper (map (willLive (x:xs)) (deadNeighbors (x:xs))) (deadNeighbors(x:xs)))

    --Új generáció kiszámítása
    stepCells :: Generation -> Generation
    stepCells [] = []
    stepCells (x:xs) = sort (stepLivingCells (x:xs) ++ stepDeadCells (x:xs))

    --Játék léptetése a megadott számú generációba
    play :: Generation -> Int -> Maybe Generation
    play [] _ = (Just [])
    play (x:xs) i
        | i < 0 = Nothing
        | i == 0 = (Just (x:xs))
        | otherwise = play (stepCells (x:xs)) (i - 1)

    --Stabil generációk detektálása
    isStill :: Generation -> Bool
    isStill [] = True
    isStill (x:xs)
          | (x:xs) == stepCells (x:xs) = True
          | otherwise = False