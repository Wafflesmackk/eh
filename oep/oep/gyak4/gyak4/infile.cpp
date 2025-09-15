#include "infile.h"
#include <iostream>
#include <fstream>
using namespace std;

infile::infile() {
	idk = true;
}

void infile::setIdk() {
	this->idk = false;
}

bool infile::getIdk() {
	return this->idk;
}

void infile::read()
{
	int k;
	std::ifstream file;
	file.open("input.txt");
	if (!file)
	{
		std::cout << "nem sikerult megnyitni a fajlt";
		exit(0);
	}
	file >> k;
	while (k != EOF) {
		if (k % 2 != 0){
			this->setIdk();
		}
		file >> k;
	}

	file.close();
	
}