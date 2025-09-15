#include "allatkereskedes.h"

void Allatkereskedes::Felvesz(Partner* P)
{
    for(unsigned int i = 0; i < partnerek.size(); i++)
    {
        if(partnerek[i] == P)
        {
            throw "error";
            //error
        }
    }

    partnerek.push_back(P);
}

void Allatkereskedes::Vesz(Allat* a, std::string d, std::string p, int e)
{
    for(unsigned int i = 0; i < allatok.size(); i++)
    {
        if(allatok[i] == a)
        {
            throw "error";
            
        }
    }
    allatok.push_back(a);
}

void Allatkereskedes::Elad(Allat* a, std::string d, std::string p, int e)
{
    for(unsigned int i = 0; i < allatok.size(); i++)
    {
        if(allatok[i] == a)
        {
           
            allatok[i]=allatok[allatok.size()-1];
            allatok.pop_back();
            return;
        }
    }

    throw "error";
    
}

bool  Allatkereskedes::VanPinty(std::string sz) const
{
    for(unsigned int i = 0; i < allatok.size(); i++)
    {
        if(allatok[i]->faj == Faj::pinty && allatok[i]->szin == sz)
        {
            return true;
        }
    }

    return false;
}

int  Allatkereskedes::HanyHorcsog() const
{
    int db=0;
    for(unsigned int i = 0; i < allatok.size(); i++)
    {
        if(allatok[i]->faj == Faj::horcsog )
        {
            db++;
        }
    }

    return db;
}

bool  Allatkereskedes::VanPartner() const
{
    if(partnerek.size()>0)
    {
        return true;
    }
    else
    {
        return false;
    }
}