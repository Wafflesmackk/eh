#pragma once
#include "bolygo.h"

class Naprendszer{
    private:
        static Naprendszer* _peldany;
        Naprendszer();
        std::vector<Bolygo*> bolygok;
    public: 
        Naprendszer* GetPeldany();
        ~Naprendszer();
        bool Maxtuzero(Csillaghajo*& cs);
        Bolygo* Vedtelen();
        void CsillaghajoAdd(Csillaghajo* p, string bolygo);
};