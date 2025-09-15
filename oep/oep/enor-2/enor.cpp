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
	_cur = x;
	if (!(_end = (sx == abnorm))){
			read();
	}
}

 
Calc Enor::doubleSum(Order  x) 
{
	Calc y;
	y.income = 0;
	y.isPancake = false;
	for (size_t i = 0; i < x.foods.size(); i++)
	{
		y.isPancake = (x.foods[i].name == "palacsinta") || y.isPancake;
		y.income = y.income + (x.foods[i].profit * x.foods[i].num);
	}
		return y;
}



void Enor::read() {
		std::string line;
		x.foods.clear();
		if (std::getline(inf, line)) {
			sx = norm;
			std::stringstream stream(line);
			stream >> x.time >> x.id;
			Food temp;
			while (stream >> temp.name >> temp.num >> temp.profit) {
				x.foods.push_back(temp);
			}


		}
		else {
			sx = abnorm;
		}
	
}