#include "infile.h"

using namespace std;

void Infile::Read(State& st, Nev1& n)
{
    st = norm; // fontos hogy az st legyen alap norm 
    string line;

    if(getline(inf,line))
    {
        istringstream stream(line);
        stream >> n.azon; // a sor elején lévő adat beolvasása pl azonosító

        n.nev.clear(); //a vektort fontos clearelni

        Nev2 n2; // struktúra létrehozása a vektorhoz

        
        //többi adat beolvasása
        while (stream >> n2.adat)
        {
           n.nev.push_back(n2);
           
        }
        
    }
    else
    {
        st = abnorm;
    }
}