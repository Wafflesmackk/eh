--Daka Bence Neptun: I5RQFI
module Homework7 where

import Data.Char

--1 feladat
sort :: Eq a => [a] -> a -> [a]
sort [] _ = []
sort (x:xs) y
    | x == y =  sort xs y
    | otherwise = x : sort xs y


listDiff :: Eq a => [a] -> [a] -> [a]
listDiff [] _ = []
listDiff a [] = a
listDiff (x:xs) (y:ys) = listDiff (sort (x:xs) y) ys

-- 2 feladat
validGame :: String -> Bool
validGame [] = True
validGame (x:xs)
    | head xs == ' ' && x /= head (tail xs) = False 
    | head xs == ' ' && x == head (tail xs) = True && validGame xs
    | tail xs == [] = True  --mert ez azt jelenti hogy az utolso elem utolso betujeig nem volt False ==> Ezutan sem lesz False mert nincs karakter ami miatt False legyen
    | otherwise = validGame xs

-- 3 feladat
middle :: [a] -> [a]
middle [] = []
middle [x] = []
middle xs = tail (init xs)

-- 4 feladat
toUpperSecondAndThird :: String -> String
toUpperSecondAndThird [] = []
toUpperSecondAndThird (x:xs)
    | xs == []  = (x:xs) 
    | tail xs == [] = [x , toUpper (head xs)]
    | otherwise = x : toUpper (head xs) : toUpper (head (tail xs)) : tail (tail xs)  




-- 5 feladat
average :: [Double] -> Double
average [a] = a
average x = sum x / len x 
    where len :: [a] -> Double
          len [] = 0
          len (x:xs) = 1 + len xs

-- 6 feladat help
removeDuplicateSpaces :: String -> String
removeDuplicateSpaces [] = []
removeDuplicateSpaces (x:xs)
    | x == ' ' && head xs == ' ' = removeDuplicateSpaces xs 
    | otherwise = x : removeDuplicateSpaces (xs)

-- 7 feladat
doubleElements :: [a] -> [a]
doubleElements [] = []
doubleElements (x:xs) = [x] ++ [x] ++ doubleElements xs

-- 8 feladat
diffCube :: Num a => a -> a -> a
diffCube x y = (x - y)^3