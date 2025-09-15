#pragma once
#include <string>
#include <fstream>
#include <vector>

struct Food {
	std::string name;
	int num;
	int profit;
};

struct Order {
	std::string time;
	std::string id;
	std::vector <Food> foods;
};

struct Calc
{
	int income;
	bool isPancake;

};

enum State{norm, abnorm};

class Enor
{
public:
	void first();

	void next();
	bool end() { return _end; }

	Order current() { return _cur; }

	Enor(const std::string& filename);
	Calc doubleSum(Order x);



private:
	bool _end;
	Order _cur;


	Order x;
	State sx;

	std::ifstream inf;
	void read();
};

