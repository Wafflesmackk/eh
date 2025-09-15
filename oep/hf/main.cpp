#include <iostream>
#include "enor.h"

using namespace std;

int main() {
	try{
		Enor enor("input.txt");
		int max = 0;
		string maxid = "start";
		Calc curSum;
		for (enor.first(); !enor.end(); enor.next()) {
			 curSum = enor.doubleSum(enor.current());
			if (curSum.isPancake && curSum.income > max)
			{
				max = curSum.income;
				maxid = enor.current().id;
			}
		}
		if (max == 0 && maxid == "start") {
			cout << "No result";
		}
		else
		{
		cout << maxid;
		}
	}

	catch (const std::runtime_error& e){
		cout << e.what();
		return 0;
	}
}