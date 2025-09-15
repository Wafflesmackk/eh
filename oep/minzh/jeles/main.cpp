#include <iostream>
#include "enor.h"

using namespace std;

int main() {
	try{
		Enor enor("input.txt");
		Data nap;
        State sx;
        enor.first();
        while (!enor.end() && enor.current().ossz < 1000)
        {
            enor.next();
        }
            int sum = 0;
            int max = 0;
            while (!enor.end())
            {
                
            }
        
        
    }
	catch (const std::runtime_error& e){
		cout << e.what();
		return 0;
	}
}