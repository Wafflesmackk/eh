#include <iostream>
#include "enor.h"

using namespace std;

int main() {
	try{
        Enor enor("inp.txt");
        int max = 0;
        string datum;
        string hely;
        int videki = 0;
        bool I = false;
        bool mind = true;
        for (enor.first(); !enor.end(); enor.next()){
            if (enor.current().fecske && !I)
            {
                I = true;
                max = enor.current().sum;
                datum = enor.current().date;
                hely = enor.current().place;
                if (enor.current().place != "Budapest")
                {
                    videki++;
                }
            }
            else if(I){
                if(max < enor.current().sum){
                    max = enor.current().sum;
                    datum = enor.current().date;
                    hely = enor.current().place;
                }
                if (enor.current().place != "Budapest")
                {
                    videki++;
                }
                

                mind = mind && (enor.current().people >= 5);
            }
        }
        cout << videki << endl;
        cout << max << " " << hely << " " << datum << " ";
        if (mind)
        {
            cout << "igaz";
        }
        else{
            cout << "hamis";
        }
        

    }
    catch (const std::runtime_error& e){
		cout << e.what();
		return 0;
	}
}

