--Daka Bence I5RQFI
module Homework4 where

    --1 feladat
    f :: [[(a,[b])]] -> Int
    f [(x,xs):[y,ys]]            = 0
    f ([_]:[(x,[xs])]:[y,ys]:[]) = 1
    f ([(x,y:_:[])]:[])          = 2

    --2 feladat
    howMany :: Char -> String -> Int
    howMany a [] = 0
    howMany a (x:xs) 
        | x == a = 1 + howMany a xs 
        | otherwise = 0 + howMany a xs

    --3 feladat
    insertAt :: Int -> a -> [a] -> [a]
    insertAt n a [] = [a]
    insertAt n a (x:xs) = take n (x:xs) ++ [a] ++ drop n (x:xs)

    --4 feladat
    letterize :: String -> [String]
    letterize [] = []
    letterize (x:xs) = [x] : letterize xs

    --5 feladat
    swapElems :: [[a]] -> [[a]]
    swapElems [] = []
    swapElems ((x:[]):y) = [x] : swapElems y 
    swapElems ((x:xs):y) = (head xs: x: tail xs) : swapElems y 

    --6 feladat
    hasZero :: [Int] -> Bool
    hasZero [] = False
    hasZero (x:xs) = x == 0 || hasZero xs

    --7 feladat
    hasEmpty :: [[a]] -> Bool
    hasEmpty [] = True
    hasEmpty (x:xs) = null x || hasEmpty xs

    --8 feladat
    doubleAll :: [Int] -> [Int]
    doubleAll [] = []
    doubleAll (x:xs) = [2*x] ++ doubleAll xs

    --9 feladat
    isLonger :: [a] -> [b] -> Bool
    isLonger [] b = False
    isLonger a [] = True
    isLonger (x:xs) (y:ys) = False || isLonger xs ys