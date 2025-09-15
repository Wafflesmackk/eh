#include <iostream>

using namespace std;

prim(int p)
{
    int i = 2;
    while ( p % i != 0 && i <= p / 2)
    {
        i++;
    }
    return(i  >= p/2);

}
int main()
{
    int k, v;
    cin >> k >> v;
    while ( k >= v)
    {
        cin >> k >> v;
    }
    int db = 0;
    for (int i = k; i <= v; i++)
    {
        if (prim(i))
        {
            db++;
        }
    }
    cout << db;
    return 0;
}
