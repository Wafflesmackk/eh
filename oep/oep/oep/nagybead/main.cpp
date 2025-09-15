#include "planet.h"

using namespace std;


int main(){
    string filename;
    cout<<"Adj meg egy fajlnevet amiben az adatok vannak"<<endl;
    cin>>filename;
    try
    {
        Planet p(filename);
        p.simulation();
    }
    catch(const exception& e)
    {
        cerr << e.what() << '\n';
    }
}