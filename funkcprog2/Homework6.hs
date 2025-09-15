--Daka Bence I5RQFI
module Homework6 where
    import Data.Char

    --1 feladat
    hasFever :: Int -> Bool
    hasFever x = (fromIntegral(x - 32) / 1.8) > 38


    --2 feladat
    positiveProduct :: (Num a, Ord a) => [a] -> a
    positiveProduct [] = 1 
    positiveProduct (x:xs)
        | x > 0 = x * positiveProduct xs
        | otherwise = positiveProduct xs


    --3 feladat
    mightyGale :: [(String, Int, Int, Int)] -> String
    mightyGale [] = ""
    mightyGale ((name,t,v,n):xs)  -- t - homerseklet, v - sebesseg, n hanyadik meres
        | v > 110 = name
        | otherwise = "" ++ mightyGale xs


    --4 feladat
    cipher :: String -> String
    cipher [] = ""
    cipher [_] = ""
    cipher (x:y:xs)
        | null xs = ""
        | isLetter x && isLetter y && isDigit(head xs)= [x,y]
        | otherwise = "" ++ cipher (y:xs)

    --5 feladat
    pizza :: [(String, Int)] -> Int
    pizza [] = 500
    pizza ((name, price):xs) = price + pizza xs
    
    --6 feladat
    inList :: Eq a => [a] -> a -> Bool  -- helper fv
    inList [] _ = False   
    inList (x:xs) y 
        | x == y = True
        | otherwise = False || inList xs y

    listDiff :: Eq a => [a] -> [a] -> [a]
    listDiff [] _ = []
    listDiff a [] = a 
    listDiff (x:xs) (y:ys)
        | inList (y:ys) x = listDiff xs (y:ys)
        | otherwise = [x] ++ listDiff xs (y:ys)

    -- 7 feladat 

    validGame :: String -> Bool
    validGame [] = True
    validGame [x] = True
    validGame  x
        | null (tail (words x)) = True 
        | (last (head (words x))) == head(head (tail (words x))) = True && validGame (tail x)
        | otherwise = False
