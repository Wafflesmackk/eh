#pragma once
#include <string>
#include "bolygo.h"

using namespace std;

class Csillaghajo{
protected: 
    string nev;
    int pajzs,pancel,urgardista;
    Csillaghajo( const string& name, int pajzs_, int pancel_, int urgardista_):
        pancel(pancel_),
        pajzs(pajzs_),
        urgardista(urgardista),
        nev(name)
    {}

public: 
    void Ved(Bolygo* b);
    virtual int Tuzero() = 0;
};

class Faltoro : public Csillaghajo{
public:
    Faltoro( const string& name, int pajzs_, int pancel_, int urgardista_): Csillaghajo(name, pajzs_, pancel_, urgardista_) {}
    int Tuzero() override  { return pancel / 2;}
};

class Lezerezo : public Csillaghajo{
public:
    Lezerezo( const string& name, int pajzs_, int pancel_, int urgardista_): Csillaghajo(name, pajzs_, pancel_, urgardista_) {}
    int Tuzero() override {return urgardista;}
};

class Partraszallo: Csillaghajo{
public:
    Partraszallo( const string& name, int pajzs_, int pancel_, int urgardista_): Csillaghajo(name, pajzs_, pancel_, urgardista_) {}
    int Tuzero() override {return pajzs;}
};