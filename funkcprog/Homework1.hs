module Homework1 where

--1 feladat Kifejezések
intExpr1 :: Int
intExpr1 = 8126

intExpr2 :: Int
intExpr2 = 2873

intExpr3 :: Int
intExpr3 = 9612

boolExpr1 :: Bool
boolExpr1 = True

boolExpr2 :: Bool
boolExpr2 = False

boolExpr3 :: Bool
boolExpr3 = True

--2.feladat Virágültetés

canPlantAll :: Int -> Bool
canPlantAll x = x `div` 13 > 0

remainingSeeds :: Int -> Int
remainingSeeds x = x `mod` 13

--3.feladat Hét

double :: Int -> Int
double x = x * 2

inc :: Int -> Int
inc x = x + 1

nulla :: Int
nulla = 0

seven1 :: Int
seven1 = inc (double 3)

seven2 :: Int
seven2 = inc ( inc (inc ( double 2)))

seven3 :: Int
seven3 = inc (inc (inc (inc (inc (inc (inc(double 0)))))))