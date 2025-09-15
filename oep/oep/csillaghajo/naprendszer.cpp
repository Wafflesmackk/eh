#include "naprendszer.h"

Naprendszer::Naprendszer(){
    bolygok.push_back(new Bolygo("Mercury"));
    bolygok.push_back(new Bolygo("Venus"));
    bolygok.push_back(new Bolygo("Earth"));
    bolygok.push_back(new Bolygo("Mars"));
    bolygok.push_back(new Bolygo("Jupiter"));
    bolygok.push_back(new Bolygo("Saturn"));
    bolygok.push_back(new Bolygo("Uranus"));   
    bolygok.push_back(new Bolygo("Neptune"));
}

Naprendszer* Naprendszer::_peldany = nullptr;

Naprendszer* Naprendszer::GetPeldany(){
    if(_peldany == nullptr){
        _peldany = new Naprendszer();
    }
    return _peldany;
}

Naprendszer::~Naprendszer(){
    if(_peldany != nullptr){
        for(Bolygo *b : bolygok){
            delete b;
        }
        _peldany = nullptr;
    }

}

bool Naprendszer::Maxtuzero(Csillaghajo*& cs){
    
    bool l = false;
    for (Bolygo* b : bolygok)
    {
        b->maxTuzero(cs, l);
        
    }
    
};

Bolygo* Naprendszer::Vedtelen(){
        for (Bolygo *b : bolygok)
        {
            if(b ->HajokSzama() == 0){
                return b;
            }
        }
        
}

void Naprendszer::CsillaghajoAdd(Csillaghajo* cs, string bolygo){
    for(Bolygo* b: bolygok){
            if(b->GetNev() == bolygo){
                b->Insert(cs);
            }
    }

}