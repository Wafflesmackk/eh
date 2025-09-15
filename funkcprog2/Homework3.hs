--Daka Bence I5RQFI
module Homework3 where
    -- 1 feladat
    olympics :: [Int]
    olympics = [x | x <- [2000..3000], x `mod` 4 == 0]


    --2 feladat
    putIntoList :: a -> [a]
    putIntoList a = [a]
    
    --3 feladat
    interval :: Int -> Int -> [Int]
    interval a b = [a..b]

    --4 feladat
    headTail :: [a] -> (a, [a])
    headTail (x:xs) = (x, xs)

    --5 feladat
    doubleHead :: [a] -> [b] -> (a, b)
    doubleHead (x:xs) (y:ys) = (x,y) 