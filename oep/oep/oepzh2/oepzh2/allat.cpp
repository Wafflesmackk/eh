#include "allat.h"

Faj Allat::SzovegbolFaj(std::string f)
{
    Faj fa;
    if(f=="horcsog")
    {
       fa = Faj::horcsog;
    }
    else if(f=="pinty")
    {
        fa = Faj::pinty;
    }
    else if(f=="tarantulla" )
    {
        fa = Faj::tarantulla;
    }
    
    return fa;
}