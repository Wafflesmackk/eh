module Gyak3 where

not' :: Bool ->Bool
not' True = False
not' False = True

and' :: Bool -> Bool ->Bool
and' True True = True
and' _ _ = False

or' :: Bool -> Bool -> Bool
or' False False = False
or' _ _ = True

xor :: Bool -> Bool -> Bool
xor True False = True
xor False True = True
xor _ _ = False

tuple1 :: (Int, Int)
tuple1 = (1,2)

isEvenTuple :: Int -> (Int, Bool)
isEvenTuple n =(n, even n)

funTuple :: (Int -> Int -> Int , Int -> Bool)
funTuple = ((*),even) 

mulTuple :: (Int, Int) -> Int
mulTuple (a, b) = a * b

swap :: (Int, Int) -> (Int,Int)
swap (a, b) = (b, a)

