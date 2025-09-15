--Daka Bence I5RQFI
module Homework9 where

    --1 feladat
    isJust :: Maybe a -> Bool
    isJust (Just a) = True
    isJust Nothing = False

    --2 feladat
    fromJust :: Maybe a -> a
    fromJust (Just a) = a
    fromJust Nothing = error "its Nothing"

    --3 feladat
    catMaybes :: [Maybe a] -> [a]
    catMaybes [] = []
    catMaybes (x:xs) 
        | isJust x = (fromJust x) : catMaybes xs 
        | otherwise = catMaybes xs

    --4 feladat
    mapMaybe :: (a -> Maybe b) -> [a] -> [b]
    mapMaybe f [] = []
    mapMaybe f x = (catMaybes [ f y | y <- x])

    --5 feladat
    type Username = String
    type Password = String

    data Privilege = Simple | Admin deriving (Eq, Show)
    data Cookie = LoggedOut | LoggedIn Username Privilege deriving (Eq, Show)
    data Entry = Entry Password Privilege [Username] deriving (Eq, Show)

    type Database = [(Username, Entry)]

    richard, charlie, carol, david, kate :: (Username, Entry)
    richard = ("Richard", Entry "password1" Admin  ["Kate"])
    charlie = ("Charlie", Entry "password2" Simple ["Carol"])
    carol   = ("Carol",   Entry "password3" Simple ["David", "Charlie"])
    david   = ("David",   Entry "password4" Simple ["Carol"])
    kate    = ("Kate",    Entry "password5" Simple ["Richard"])

    testDB :: Database
    testDB = [ richard, charlie, carol, david, kate ]

    testDBWithoutCarol :: Database
    testDBWithoutCarol = [ ("Richard", Entry "password1" Admin  ["Kate"]) , ("Charlie", Entry "password2" Simple []) , ("David",   Entry "password4" Simple []), ("Kate",    Entry "password5" Simple ["Richard"])]

    --6 feladat
    password :: Entry -> Password
    password (Entry pas priv friends) = pas

    --7 feladat
    privilege :: Entry -> Privilege
    privilege (Entry pas priv friend) = priv

    --8 feldat
    friends :: Entry -> [Username]
    friends (Entry pas priv friend) = friend

    --9 feladat
    mkCookie :: Username -> Password -> Entry -> Cookie
    mkCookie user pas ent 
        | (password ent) == pas = (LoggedIn user (privilege ent))
        | otherwise = (LoggedOut)

    --10 feladat
    login :: Username -> Password -> Database -> Cookie
    login user pas dat 
        | isJust (lookup (user) dat) = mkCookie user pas (fromJust(lookup (user) dat))
        | otherwise = LoggedOut

    --11 feladat
    removeUserFromList :: Username -> [Username] -> [Username]
    removeUserFromList a [] = []
    removeUserFromList a (x:xs)
        | a == x = removeUserFromList a xs
        | otherwise = x : removeUserFromList a xs



    updateEntry :: Username -> (Username, Entry) -> Maybe (Username, Entry)
    updateEntry delUser (user, (Entry pass priv userList))
        | delUser == user = Nothing
        | otherwise = Just (user, (Entry pass priv (removeUserFromList delUser userList) ))

    --12 feladat
    privilegeCookie :: Cookie -> Privilege
    privilegeCookie LoggedOut = Simple
    privilegeCookie (LoggedIn user priv) = priv



    deleteUser :: Cookie -> Username -> Database -> Database
    deleteUser LoggedOut delUser db = db
    deleteUser cookie delUser db 
        | privilegeCookie(cookie) == Admin = mapMaybe(updateEntry delUser) db
        | otherwise = db