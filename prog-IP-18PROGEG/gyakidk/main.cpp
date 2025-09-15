#include <iostream>
#include <math.h>

using namespace std;

struct coor
{
    int x;
    int y;

};

double tav (coor kozep, coor pont)
{
    return sqrt(pow((kozep.x-pont.x),2) + pow((kozep.y-pont.y),2));
}


int main()
{
    /*
    string s;
    cin >> s;
    int h = s.size();
    int k;
    for(int i=0; i < h; i++)
    {
        int c = (int) s[i];
        c-= 48;
        k *= 10;
        k += c;
    }
    cout << k;*/
/*
    int n;
    cin >> n;
    int t[n];
    int o=0;

    for (int i = 0; i < n; i++)
    {
        cin >> t[i];
        while( t[i] < 0)
        {
            cout << "Rossz erték ";
            cin >> t[i];
        }
        o += t[i];
    }
    cout << "Osszeg "<< o << endl;
    cout <<" Atlag " << (float) o / n <<endl;
    */
    coor pont;
    coor kpont;
    double r;
    int n;
    int db = 0;
    cin >> r >> kpont.x >> kpont.y >> n;
    coor t[n];
    int e[n];
    for (int i = 0; i < n; i++)
    {
    cin >> t[i].x >> t[i].y;
    double t = tav(kpont,pont);
    cout << t <<endl;

    /*if (r > t)
    {
        cout<< "Benne van";
    }
    else
    {
        cout<< "Nincs benne";
    }*/
    }
    return 0;
}
