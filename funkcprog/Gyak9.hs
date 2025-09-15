module Gyak9 where

    data Point2D = P2D Int Int deriving (Show, Eq, Ord)

    pont :: Point2D
    pont = P2D 5 6

    getx :: Point2D -> Int
    getx (P2D x y) = x 

    data Point2DList = P2DL [Point2D] deriving (Show, Eq, Ord)
    sample :: Point2DList
    sample = P2DL [P2D 5 6, P2D 7 8, P2D 11 8]

    foo :: Point2DList -> Point2DList
    foo (P2DL ((P2D x y):xs)) = P2DL [(P2D (x+y) (x+y)), (P2D x y)]

    data Days = Mon | Tue | Wed | Thu | Fri | Sat | Sun 
    szombat :: Days
    szombat = Sat

    data SimpleColors = Red | Green | Blue

    redSimple :: SimpleColors
    redSimple = Red

    instance Show Days where
        show Mon = "Hetfo"
        show Tue = "Kedd"
        show Wed = "Szerda"
        show Thu = "Csutortok"
        show Fri = "Pentek"
        show Sat = "Szombat"
        show Sun = "Vasarnap"



    data Color = RGB Int Int Int | HSL Int Int Int
    
    whiteRGB :: Color
    whiteRGB = RGB 255 255 255

    whiteHSL :: Color
    whiteHSL =  HSL 42 13 100


    instance Show Color where
        show (RGB r g b) = "My Color RGB " ++ show r ++ " " ++ show g ++ " " ++ show b   
        show (HSL h s l) = "My Color HSL " ++ show h ++ " " ++ show s ++ " " ++ show l

    instance Eq Color where
        (==) (RGB r1 g1 b1) (RGB r2 g2 b2) = r1 == r2 && g1 == g2 && b1 == b2
        (==) (HSL h1 s1 l1) (HSL h2 s2 l2) = h1 == h2 && s1 == s2 && l1 == l2
        (==) _ _ = False

    data Maybee a = Nothingg | Justt a deriving (Eq, Ord, Show)

    isNothing :: Maybee a -> Bool
    isNothing Nothingg = True
    isNothing _ = False

    isJust :: Maybee a -> Bool
    isJust (Justt x) = True
    isJust _ = False

    safeHead :: [a] -> Maybee a
    safeHead [] = Nothingg
    safeHead (x:xs) = Justt x 
    --safeHead l = Justt (head l)

    --mul :: (a -> b) -> [a] -> [b]
    --mul f [] = []
    --mul f (x:xs) = [f x] ++ mul f xs    
