module Gyak5 where
--import Prelude hiding (++)

init' :: [a] -> [a]
init' [_] = []
init' (x:xs) = x : init' xs

minimum' :: Ord a => [a] -> a
minimum' [x] = x 
minimum' (x:xs) = min x (minimum' xs)  

--(@@) :: [a] -> [b] -> [a]
--(@@) [] [] = []
--(@@) u [] = u
--(@@) [] v = v
--(@@) (x:xs) v = x : (xs ++ v)

max' :: Ord a => a -> a -> a
max' x y
 |x > y = x
 |otherwise = y

sgn :: Int -> Int
sgn x
	| isNegative x = -1
	| x == 0
	| x > zero = 1
	where isNegative :: Int -> Bool
	isNegative x = x < 0
	zero = zero
