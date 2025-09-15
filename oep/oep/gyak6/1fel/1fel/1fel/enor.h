#pragma once
#include <string>
#include <fstream>


enum State {norm, abnorm};

class Enor{
public:
	Enor(const std::string& filename);
	void next();
	int current() { return _cur; }
	void first();
	bool end() { return _end; }


private:
	std::ifstream inf;
	bool _end;
	int _cur;


	char x;
	State sx;
	void read();
};

