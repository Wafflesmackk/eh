#include "bolygo.h"

    bool  Bolygo::maxTuzero(Csillaghajo*& cs, bool& l){
        bool l = false;
        for (Csillaghajo* hajo : hajok)
        {
            if(!l){
               l = true;
               cs = hajo;
            }
            if(l && cs->Tuzero() < hajo->Tuzero()){
                cs = hajo;
            }
        }
        return l;
    }
    int Bolygo::OsszPajzs() const{
        int sum = 0;
        for (Csillaghajo* hajo : hajok)
        {
            for (Csillaghajo* hajo : hajok)
        {
            sum = hajo->Tuzero();
        }
        }
        

    }
    void Bolygo::Insert(Csillaghajo* cs){
        hajok.push_back(cs);
    }