#include "naprendszer.h"
#include "bolygo.h"
#include "csillaghajo.h"
#include <iostream>
#include <fstream>


using namespace std;

void read(Naprendszer* n, const string& filname){
    std::ifstream inf(filname);
    if(inf.fail()){
        exit(-1);
    }
    string bolygo, tipus, nev;
    int pancel,pajzs,urgardista;
    while (inf >> bolygo >> tipus >> nev>> pancel>> pajzs >> urgardista)
    {
        if(tipus == "Lander"){
            Partraszallo* p = new Partraszallo(nev,pajzs,pancel,urgardista);
        }
        if(tipus == "Breaker"){
            Faltoro* p = new Faltoro(nev,pajzs,pancel,urgardista);
        }
        if(tipus == "Lander"){
            Partraszallo* p = new Partraszallo(nev,pajzs,pancel,urgardista);
        }
    }
    
} 

int main(){

    Naprendszer* naprendszer = Naprendszer::GetPeldany();

 }