#include <iostream>
#include "enor.h"

using namespace std;

int main() {
	try{
		Enor enor("input.txt");
		int max = 0;
		string maxid = "start";
        bool is5000 = false;
		for (enor.first(); !enor.end(); enor.next()) {
			is5000 = is5000 || (enor.current().newInfected > 5000);
            if (max < enor.current().ossz)
            {
                max = enor.current().ossz;
                maxid = enor.current().date;
            }
		
		}
		if (max == 0 && maxid == "start") {
			cout << "No result";
		}
		else if (is5000)
		{
			cout << "yes " << maxid;
		}
		else
		{
		cout << "no " << maxid;
		}
	}

	catch (const std::runtime_error& e){
		cout << e.what();
		return 0;
	}
}