#include "enor.h"
#include <iostream>
#include <sstream>
#include <stdexcept>


Enor::Enor(const std::string& filename) {
	inf.open(filename);
	if (inf.fail()) {
		throw std::runtime_error("Could not open file, or did not find file");
	}
}


void Enor::first() {
	read();
	next();
}


void Enor::next() {
	_current = x;
	if (!(_end = (sx == abnorm))){
			read();
	}
}





void Enor::read() {
	std::string line;
	if(std::getline(inf,line)){
		sx = norm;
		std::stringstream stream(line);
		stream >> x.date >> x.newInfected;
		int sicks;
		int mechL;
		while (stream >> line >> sicks >> mechL){
			x.ossz += sicks;
		}
		

	}
	else{
		sx = abnorm;
	}
}