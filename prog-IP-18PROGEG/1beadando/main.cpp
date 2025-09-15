#include <iostream>
using namespace std;


struct tanar
{
    int TS;
    int Nap;
    int Ora;
};


int main()
{
    int N;
    int O;
    //elso sor beolvasasa
    cin >> O >> N;
    tanar t[999];
    while (O < 1 || O > 1000 || N < 1 || N > 100) // adatok ellenorzese hogy megfelel-e a leirtaknak
    {
        cout << "Hibas adat" << endl;
        cin.clear();
        cin.ignore(32676, '\n');
        cin >> O >> N;
    }
    //tobbi adat beolvasasa
    int napokSum[5];
    int Nap[5];
    for (int i = 0; i < 5; i++)
    {
        napokSum[i] = 0;
        Nap[i] = 0;
    }
    for (int i = 0; i < O; i++)
    {
        cin >> t[i].TS >> t[i].Nap >> t[i].Ora;
        while (t[i].TS > N || t[i].TS < 1 || t[i].Nap < 1 || t[i].Nap > 5 || t[i].Ora < 0 || t[i].Ora > 8) //beolvasott adatok ellenorzese
        {
            cout << "hibás adat" << endl;
            cin.clear();
            cin.ignore(32676, '\n');
            cin >> t[i].TS >> t[i].Nap >> t[i].Ora;
        }
    }

    int elTs = 0;
    int elNap = 0;
    int ind;
    for (int i = 0; i < O; i++)
    {
        if(t[i].TS == elTs)
        {
            if (t[i].Nap != elNap)
            {
                ind = t[i].Nap - 1;
                if (Nap[ind] != 1) //ellenorizzuk hogy azt a napot nem szamoltuk-e mar (ertsd ha valaki megad 2 keddi ora kozott egy szerdait)
                {
                    napokSum[ind]++;
                    Nap[ind] = 1;
                }
                elNap = t[i].Nap;
                elTs = t[i].TS;
            }
        }
        else
        {
            for (int i = 0; i < 5; i++)
            {
                Nap[i] = 0;
            }

            ind = t[i].Nap - 1;
            Nap[ind] = 1;
            napokSum[ind]++;
            elNap = t[i].Nap;
            elTs = t[i].TS;
        }
    }



    for (int i = 0; i <= 4; i++)
    {
        cout << napokSum[i] << " ";
    }


    return 0;
}
