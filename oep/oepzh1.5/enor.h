#pragma once
#include <string>
#include <fstream>
#include <vector>


struct Bird{
    std::string name;
    int birdNum;
};

struct Observe{
    std::string place;
    std::string date;
    int people;
    std::vector <Bird> birds;
    int sum = 0;
    bool fecske = false;
};
enum State{norm,abnorm};

class Enor{
    public:
    void first();
    void next();

    bool end() { return _end; }
    Observe current() {return _cur;}

    Enor(const std::string& filename);

    private:
    bool _end;
    Observe _cur;

    Observe x;
    State sx;

    std::ifstream inf;
    void read();
};