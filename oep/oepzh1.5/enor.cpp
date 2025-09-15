#include "enor.h"
#include <iostream>
#include <sstream>
#include <stdexcept>
using namespace  std;


Enor::Enor(const std::string& filename) {
	inf.open(filename);
	if (inf.fail()) {
		throw std::runtime_error("error with file (did not find or couldnt open)");
	}
}

void Enor::first() {
	read();
	next();
}

void Enor::next(){
	_cur = x;
	if (!(_end = (sx == abnorm))){
			read();
	}
}



void Enor::read() {
		std::string line;
		x.birds.clear();
		x.fecske = false;
        x.sum = 0;
		if (std::getline(inf, line)) {
			sx = norm;
			std::stringstream stream(line);
			stream >> x.place >> x.date >> x.people;
			Bird temp;
			while (stream >> temp.name >> temp.birdNum) {
                x.sum += temp.birdNum;
				if (temp.name == "fecske")
				{
					x.fecske = true; 
				}
				
				x.birds.push_back(temp);
			}
		}
        
		else {
			sx = abnorm;
		}
	
}