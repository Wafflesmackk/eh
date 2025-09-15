-- Daka Bence I5RQFI
module Homework7 where

    --1 feladat
    data Vector3 =  V Int Int Int deriving (Show, Eq)

    --2 feladat
    componentSum :: Vector3 -> Int
    componentSum (V x y z) = x + y + z


    --3 feladat
    crossProduct :: Vector3 -> Vector3 -> Vector3
    crossProduct (V a b c) (V x y z) = (V (b*z - c*y) (c*x - a*z) (a*y - b*x))

    --4 feladat
    vectorListSum :: [Vector3] -> Vector3
    vectorListSum [] = (V 0 0 0)
    vectorListSum [a] = a
    vectorListSum ((V a b c) : (V x y z) : xs) = vectorListSum ((V (a+x) (b+y) (c+z)) : xs)

    --5 feladat
    data Storage = HDD String Int Int | SSD String Int deriving (Show, Eq)

    --6 feladat
    capacity :: Storage -> Int
    capacity (HDD x y z) = z
    capacity (SSD x y) = y

    --7 feladat
    isHDD :: Storage -> Bool
    isHDD (HDD x y z) = True
    isHDD _ = False

    --8 feladat
    maxSSD :: [Storage] -> Int
    maxSSD [] = 0
    maxSSD ((HDD a b c) : xs) = maxSSD xs
    maxSSD ((SSD a b):xs) 
        | maxSSD (xs) > b = maxSSD xs
        | otherwise = b 

    hugeHDDs :: [Storage] -> [Storage]
    hugeHDDs [] = []
    hugeHDDs ((SSD x y) :xs) = hugeHDDs xs
    hugeHDDs ((HDD a b c):xs) 
        | c > maxSSD xs  = [(HDD a b c)] ++ hugeHDDs xs 
        | otherwise =  hugeHDDs xs