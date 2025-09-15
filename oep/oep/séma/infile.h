#pragma once 
//általában ezeket az includeokat használjuk
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
//---------------------------------------

//A fájl állapotát tartjuk vele számon a read metódusnál van szükség rá (abnorm=fájl vége)
enum State
{
    norm,abnorm
};
//----------------------------------

//ha valamiből több van (pl mérés adatokkal annak az adatait itt adjuk meg)
struct Nev2
{
    int adat;
};
//-------------------------------------

struct Nev1
{
  //ide azok az adatok jönnek amiből csak 1 van egy sorba pl dátum,azonosító
   std:: string azon; // sorazonosító
   std::vector<Nev2> nev; 

};

class Infile
{
    public:
    Infile (const std::string& filename) : inf(filename) {}; //konstruktor minden file beolvasásnál ugyanúgy hagyjuk
    void Read(State& st, Nev1& n); //nagy valószínűséggel ezt is lehet így hagyni 
    
    private:
    std::ifstream inf;

};