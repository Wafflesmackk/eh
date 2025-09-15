--Daka Bence I5RQFI
module Homework5 where
    --1 feladat
    geometricSequence :: Num a => a -> a -> [a]
    geometricSequence a b = [a] ++ geometricSequence (a*b) b

    --2 feladat
    isSorted :: Ord a => [a] -> Bool
    isSorted [] = True
    isSorted [_] = True
    isSorted (x:xs) = x < head xs && isSorted xs


    --3 feladat
    fromTo :: Int -> Int -> [a] -> [a]
    fromTo a b [] = []
    fromTo a b (x:xs) = drop a (take b (x:xs))

    --4 feladat
    runs :: Int -> [a] -> [[a]]
    runs _ [] = []
    runs a (x:xs) = (take a (x:xs)) : runs a (drop a (x:xs))

    --Bonus
    --uniques :: Eq a => [a] -> [a]
    --uniques [] = []
    --uniques (x:xs)
    --    | null xs = [x]
    --    | x == head xs = uniques xs
    --    | otherwise = x : uniques xs  