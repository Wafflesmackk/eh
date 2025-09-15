#include <iostream>
#include <fstream>
#include <string>
#include "infile.h"
using namespace std;

int main() {
	 //string k = "input.txt";
	 infile a;
	 
	 a.read();

	 cout << a.getIdk();


}