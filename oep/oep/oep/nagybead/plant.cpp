#include "plant.h"



string Puffancs::getName() {return this->name;}
int Puffancs::getNutrient() {return this->nutrient;}
string Puffancs::getType(){return "Puffancs";}
bool Puffancs::isAlive() {return this->alive;}

void Puffancs::AlphaIn(){
    this->nutrient = this->nutrient + 2;
    if(this->nutrient < 1 || this->nutrient > 10){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Puffancs::DeltaIn(){
    this->nutrient = this->nutrient - 2;
    if(this->nutrient < 1 || this->nutrient > 10){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Puffancs::noRadIn(){
    this->nutrient = this->nutrient -1;
    if(this->nutrient < 1 || this->nutrient > 10){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}
 int Puffancs::AlphaReq() {return 10;}
 int Puffancs::DeltaReq() {return 0;}



string Deltafa::getName() {return this->name;}
int Deltafa::getNutrient() {return this->nutrient;}
string Deltafa::getType(){return "Deltafa";}
bool Deltafa::isAlive() {return this->alive;}


void Deltafa::AlphaIn(){
    this->nutrient = this->nutrient - 3;
    if(nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Deltafa::DeltaIn(){
    this->nutrient = this->nutrient + 4;
    if(nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Deltafa::noRadIn(){
    this->nutrient = this->nutrient -1;
    if(this->nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}
 int Deltafa::AlphaReq() {return 0;}

int Deltafa::DeltaReq(){
    if(this->nutrient < 5){
        return 4;
    }
    else if(this-> nutrient > 5 && this->nutrient < 10){
        return 1;
    }
    else{
        return 0;
    }
}



string Parabokor::getName() {return this->name;}
int Parabokor::getNutrient() {return this->nutrient;}
string Parabokor::getType(){return "Parabokor";}
bool Parabokor::isAlive() {return this->alive;}

void Parabokor::AlphaIn(){
    this->nutrient = this->nutrient + 1;
    if(nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Parabokor::DeltaIn(){
    this->nutrient = this->nutrient + 1;
    if(nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}

void Parabokor::noRadIn(){
    this->nutrient = this->nutrient - 1;
    if(nutrient < 1){
        this->alive = false;
    }
    else{
        this->alive = true;
    }
}
 int Parabokor::AlphaReq() {return 0;}
 int Parabokor::DeltaReq() {return 0;}
