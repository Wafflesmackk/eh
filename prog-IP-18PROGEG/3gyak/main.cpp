#include <iostream>

using namespace std;

int main()
{
    /*
    const string honap[12] = {"jan", "feb", "marc", "apr", "maj", "jun", "jul", "aug", "sep", "okt", "nov", "dec"};
    int a;
    cin >> a;
    while (a<1 || a>12 || cin.fail())
    {
        cout<< "Hibas sorszam!"<<endl;
        cin.clear();
        cin.ignore();
        cin >> a;
    }
    cout << honap[a-1];
    */
/*
    const string het[7] = {"hetfo", "kedd", "szerda", "csutortok", "pentek", "szombat", "vasarnap"};
    string nap;
    cin >> nap;
    int a;
    cin >> a;
    int i = 0;
    while(het[i] != nap)
    {
        i++;
    }

    i += a;
    i--;
    i = i % 7;
    cout<< het[i];
    */

    /*
    int n;
    cin >> n;
    int t[n];
    int o = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> t[i];
        o += t[i];
    }
    cout << o;
    */
    int n;
    cin >> n;
    const int ev = 2021;
    int kor[n];
    bool van = false;
    for(int i = 0; i < n; i++ )
    {
        cin >> kor[i];
        if (ev - kor[i] < 18 )
        {
            van = true;
        }
    }
    if (van)
    {
        cout << "Van" << endl;
    }
    else
    {
        cout << "Nem" <<endl;
    }
    return 0;
}
