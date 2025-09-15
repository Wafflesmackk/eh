--Daka Bence I5RQFI
module Homework8 where
    import Data.Char

    testFile0 :: File 
    testFile0 = ["asd  qwe", "-- Foo", "", "\thello world "]
    
    type Line = String
    type File = [Line]
    
    --1 feladat
    countWordsInLine :: Line -> Int
    countWordsInLine x = length (words x)

    --2 feladat
    countWords :: File -> Int
    countWords [] = 0
    countWords (x:xs) = sum (map countWordsInLine (x:xs))

    --3 feladat
    countCharsInLine :: Line -> Int
    countCharsInLine [] = 0
    countCharsInLine (x:xs) = length (x:xs)

    countChars :: File -> Int
    countChars [] = 0
    countChars (x:xs) = sum (map countCharsInLine (x:xs))

    
    --4 feladat
    capitalizeWord :: String -> String
    capitalizeWord [] = []
    capitalizeWord (x:xs) = (toUpper x) : xs

    capitalizeWordsInLine :: Line -> Line
    capitalizeWordsInLine [] = []
    capitalizeWordsInLine a = unwords (map capitalizeWord (words a))


    --5 feladat
    isComment :: Line -> Bool
    isComment "" = False
    isComment (x:xs)
        | null xs = False
        | x == '-' && head xs == '-' = True
        | otherwise = False

    --6 feladat
    notComment :: Line -> Bool
    notComment "" = True
    notComment (x:xs)
        | null xs = True
        | x == '-' && head xs == '-' = False
        | otherwise = True

    dropComments :: File -> File
    dropComments [] = []
    dropComments x = filter (notComment) x

    --7 feladat
    lineNumber :: Int -> Line -> Line
    lineNumber a x = (show a) ++ ": " ++ x

    numberLines :: File -> File
    numberLines [] = []
    numberLines x = zipWith lineNumber (iterate (1+) 1)  x
    
    --8 feladat
    dropTrailingWhitespaces :: Line -> Line
    dropTrailingWhitespaces [] = [] 
    dropTrailingWhitespaces a = reverse (helper a)
    
    helper :: Line -> Line
    helper a = dropWhile (isSpace) (reverse a) 

    --9 feladat
    spaceGenerator :: Int -> [Char]
    spaceGenerator 0 = []
    spaceGenerator a = last (take (a+1) (iterate (" "++) ""))

    replaceTab :: Int -> Char -> [Char]
    replaceTab a x 
        | x == '\t' = spaceGenerator a
        | otherwise = [x]

    --10 feladat
    replaceTabInLine :: Int -> Line -> Line
    replaceTabInLine _ [] = []
    replaceTabInLine a x = concatMap (replaceTab a) x

    replaceTabs :: Int -> File -> File
    replaceTabs _ [] = []
    replaceTabs a (x:xs) = map (replaceTabInLine a) (x:xs)