#pragma once 
#include "partner.h"
#include "allat.h"

#include <string>
#include <vector>

class Allatkereskedes
{
public:
    std::vector<Allat*> allatok;
    std::vector<Partner*> partnerek;
    
    Allatkereskedes() {}
    void Felvesz(Partner* P);
    void Vesz(Allat* a, std::string d, std::string p, int e);
    void Elad(Allat* a, std::string d, std::string p, int e);
    bool  VanPinty(std::string sz)const;
    int  HanyHorcsog()const;
    bool VanPartner()const;



};