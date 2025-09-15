#include <iostream>
#include "enor.h"

using namespace std;

int main() {
	try{
        Enor enor("inp.txt");
        int min = 0;
        string datum;
        bool I = false;
        bool mind = true;
        for (enor.first(); !enor.end(); enor.next()){
            if (enor.current().place == "Budapest" && !I)
            {
                I = true;
                min = enor.current().sum;
                datum = enor.current().date;
            }
            else if(enor.current().place == "Budapest" && I){
                if(enor.current().sum < min){
                    min = enor.current().sum;
                    datum = enor.current().date;
                }
            }
            mind = mind && ((enor.current().sum / enor.current().people) >= 10);
        }

        if (I)
        {
            cout << datum << " " << min;
        }
        else{
            cout << "nincs";
        }


        if (mind)
        {
            cout << " igaz";
        }
        else{
            cout << " hamis";
        }
        
        

    }
    catch (const std::runtime_error& e){
		cout << e.what();
		return 0;
	}
}

