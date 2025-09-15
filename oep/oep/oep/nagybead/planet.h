#pragma once
#include <string>
#include <vector>
#include "plant.h"
#include <iostream>
#include <fstream>

using namespace std;

class Planet
{
private:
    int plantCount;
    int days;
    vector<Plant*> plants;
    ifstream inf;
public:
    Planet(int plantCount_, int days_);
    Planet(const string& filename);
    void addPlant(Plant* p);
    void simulation();
    ~Planet();
};