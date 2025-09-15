#include "enor.h"
#include <iostream>

using namespace std;

int main() {
	Enor enor("input.txt");
	int db = 0;

	for (enor.first(); !enor.end(); enor.next()) 
	{
		if (enor.current() >= 12) {
			db += 2;
		}
		else {
			db++;
		}
	}

	cout << "szavak szama: " << db << endl;


	return 0;
}