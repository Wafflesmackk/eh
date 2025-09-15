#pragma once
#include <string>
#include <vector>

using namespace std;

class Plant
{
protected:
    string name;
    int nutrient;
    bool alive;
public:
    Plant(string name_, int nutrient_, bool alive_) : name(name_), nutrient(nutrient_), alive(alive_) {};
    virtual string getName() {return name;}
    virtual int getNutrient() {return nutrient;}
    virtual string getType() = 0;
    virtual bool isAlive() = 0;
    virtual void AlphaIn() = 0;
    virtual void DeltaIn() = 0;
    virtual void noRadIn() = 0;
    virtual int AlphaReq() = 0;
    virtual int DeltaReq() = 0;
};


class Puffancs : public Plant
{
public:
    Puffancs(string name_, int nutrient_, bool alive_) : Plant(name_, nutrient_, alive_){};
    string getName() override;
    int getNutrient() override;
    string getType() override;
    bool isAlive() override;
    void AlphaIn() override;
    void DeltaIn() override;
    void noRadIn() override;
    int AlphaReq() override;
    int DeltaReq() override;
};

class Deltafa : public Plant
{
public:
    Deltafa(string name_, int nutrient_, bool alive_) : Plant(name_, nutrient_, alive_){};
    string getName() override;
    int getNutrient() override;
    string getType() override;
    bool isAlive() override;
    void AlphaIn() override;
    void DeltaIn() override;
    void noRadIn() override;
    int AlphaReq() override;
    int DeltaReq() override;
};


class Parabokor : public Plant
{
public:
    Parabokor(string name_, int nutrient_, bool alive_) : Plant(name_, nutrient_, alive_){};
    string getName() override;
    int getNutrient() override;
    string getType() override;
    bool isAlive() override;
    void AlphaIn() override;
    void DeltaIn() override;
    void noRadIn() override;
    int AlphaReq() override;
    int DeltaReq() override;
};

