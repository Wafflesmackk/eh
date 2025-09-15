--Daka Bence, Neptun: I5RQFI
module Homework4 where

--1 feladat
headTail :: [a] -> (a, [a])
headTail a = (head a, tail a)

--2 feladat
doubleHead :: [a] -> [b] -> (a, b)
doubleHead a b = (head a, head b)

--3 feladat
hasZero :: [Int] -> Bool
hasZero [] = False 
hasZero (x:xs) =  x == 0 || hasZero xs 

--4 feladat
hasEmpty :: [[a]] -> Bool
hasEmpty [] = False
hasEmpty (x:xs) = length (x) == 0 || hasEmpty xs

--5 feladat
doubleAll :: [Int] -> [Int]
doubleAll [] = []
doubleAll (x:xs) = (x*2):(doubleAll xs) 

--6 feladat
--isLonger :: [a] -> [b] -> Bool
--isLonger a b = length a > length b
--isLonger (x:xs) (y:ys) = 
 