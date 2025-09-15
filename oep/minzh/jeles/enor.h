#pragma once
#include <string>
#include <fstream>
#include <vector>

struct Hospital
{
    std::string name;
    int sick;
    int onMechLung;
};



struct  Data
{
    std::string date;
    int newInfected;
    std::vector <Hospital> hospitals;
    int ossz = 0;
    
};


enum State{norm, abnorm};

class Enor
{
public:
	void first();

	void next();
	bool end() { return _end; }
    Data current() {return _current;};

    Enor(const std::string& filename);

private:
	bool _end;
	Data _current;


    Data x;
	State sx;

	std::ifstream inf;
	void read();
};

