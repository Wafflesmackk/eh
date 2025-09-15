#include <iostream>
#include <fstream>
#include <sstream>

#define print(X) std::cout << X << std::endl;

#include "allatkereskedes.h"

using namespace std;


int main(){
    Allatkereskedes ak;

    std::ifstream f;
    f.open("input.txt");
    if (f.fail()){
        cerr << "Nem letezo fajl" << endl;
    }

    std::string sor;
    getline(f, sor);
    {
        std::istringstream ss(sor);

        std::string nevTmp;
        while (ss >> nevTmp){
            ak.Felvesz(new Partner(nevTmp));
        }
    }


    int allatSzam;
    getline(f, sor);
    {
        std::istringstream ss(sor);
        ss >> allatSzam;
    }

    std::string nev, faj, szin;
    for (unsigned int i = 0; i < allatSzam; ++i){
        getline(f,sor);
        std::istringstream ss(sor);
        ss >> nev >> faj >> szin;
        ak.Vesz(new Allat(nev,faj,szin), "", "", 0);
    }

    std::string keresettSzin;
    f >> keresettSzin;

    cout << (ak.VanPinty(keresettSzin)?"van":"nincs") << ' ';
    cout << ak.HanyHorcsog() << ' ';
    cout << (ak.VanPartner()?"van":"nincs") << endl;
    return 0;
}