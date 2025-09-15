#include "enor.h"




Enor::Enor(const std::string& filename) {
	inf.open(filename);
	//error throw
}

void Enor::next() {
	while (sx == norm && (x == ' ' || x == '\n')){
		read();
	}
	_end = sx == abnorm;
	if (!_end) {
		_cur = 0;
		while (sx == norm && !(x == ' ' || x == '\n')){
			_cur++;
			read();
		}
	}

}

void Enor::first() {
	read();
	next();
}



void Enor::read() {
	inf.get(x);
	sx = inf.eof() ? abnorm : norm;
}