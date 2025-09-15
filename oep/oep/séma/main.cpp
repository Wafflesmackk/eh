#include "infile.h"

using namespace std; 

int main()
{
    //fontos hogy példányosítsuk az osztály ,Statet és a struct-ot
    Infile f("inp.txt"); 
    Nev1 n;
    State st;

    while (st == norm)
    {
    
        //feladat kidolgozása xd

        
        f.Read(st,n);
    }

    //ha kell az hogy hibát írjon ha nem tudja megnyitni a filet
    
    /*try
    {
        Infile f("inp.txt"); 
        Nev1 n;
        State st;

        while (st == norm)
        {
            f.Read(st,n);
        }
    }
    catch(FileNotFoundException e)
    {
        std::cerr <<"Nem sikerült megnyitni a filet!\n";
    }*/
    
    
    return 0;
}