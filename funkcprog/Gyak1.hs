module Gyak1 where

one :: Int
one = 1



add :: Int -> Int -> Int 
add x y = x + y
minus :: Num a => a -> a ->a
minus x y = x -y

paros :: Int -> Bool
paros x = x `mod` 2 == 0

paratlan :: Int -> Bool
paratlan x = not (paros x)

inc :: Int -> Int
inc x = x + 1

incTwice :: Int -> Int
incTwice x = inc (inc x)

area :: Int -> Int -> Int
area x y = x* y