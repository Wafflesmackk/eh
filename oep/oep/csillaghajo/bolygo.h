#pragma once
#include <csillaghajo.h>
#include <vector>
#include <string>

using namespace std;

class Bolygo
{
private:
    string nev;
    vector<Csillaghajo*> hajok;
    
public:
    Bolygo(const string name): nev(name) {};
    bool maxTuzero(Csillaghajo*& cs, bool& l);
    int OsszPajzs() const;
    void Insert(Csillaghajo* cs);
    int HajokSzama() {return hajok.size();}
    string GetNev() {return nev;}
};