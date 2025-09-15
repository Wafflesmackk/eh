#pragma once
#include "faj.h"
#include <string>

class Allat
{
public:
    std::string azon;
    Faj faj;
    std::string szin;

    Faj SzovegbolFaj(std::string f);
    Allat(std::string a, std::string f, std::string sz) : azon(a), faj(SzovegbolFaj(f)),szin(sz) {}
};