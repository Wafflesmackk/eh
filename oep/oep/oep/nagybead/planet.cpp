#include "planet.h"

using namespace std;

Planet::Planet(int plantCount_, int days_){
    if(plantCount_ <= 0 || days <= 0){
        throw runtime_error("Invalid argument");
    }
    else{
        this->plantCount = plantCount_;
        this->days = days_;
    }
}

void Planet::addPlant(Plant* p){
    this->plantCount++;
    this->plants.push_back(p);
}

Planet::Planet(const string& filename){
    this->inf.open(filename);
	if (this->inf.fail()) {
		throw std::runtime_error("Could not open file, or did not find file");
	}
    else{
        int count;
        inf >> count;
        if(count <= 0){
            throw runtime_error("Invalid argument");
        }

        this->plantCount = count;
        string name;
        char type;
        int nutrient;
        for (size_t i = 0; i < count; i++)
        {
            inf >> name >> type >> nutrient;
            if (type == 'p')
            {
                Puffancs* p = new Puffancs(name,nutrient,(nutrient > 0));
                plants.push_back(p);
                //cout<< p->getType() <<endl;
            }
            else if(type == 'd'){
                Deltafa* d =  new Deltafa(name,nutrient,(nutrient > 0));
                plants.push_back(d);
                //cout << d->getType() << endl;
            }
            else if(type == 'b'){
                Parabokor* b = new Parabokor(name,nutrient,(nutrient > 0));
                plants.push_back(b);
               //cout<< b->getType() << endl;
            }
            else{
                throw runtime_error("Invalid argument");
            }
            
        }
        int day;
        inf >> day;
        if(day <= 0){
        throw runtime_error("Invalid argument");
        }
        this->days = day;
    /*
        for (int i = 0; i < count; i++)
        {
            cout<< plants[i]->getName()<<" "<< plants[i]->getType() << endl;
        }
      */  
        
    }
}

void Planet::simulation(){
    int noRadDays = 0;
    int diff = 0;
    for (size_t i = 0; i <= days; i++)
    {
        cout<< i << ". nap kezdés" << endl;
        if (diff >= 3)
        {
            cout<< "\t Ma Alfa sugárzás volt" << endl;
                noRadDays = 0;
        }
        else if(diff <= -3){
            cout << "\t Ma Delta sugárzás volt"<< endl;
            noRadDays = 0;

        }
        else if(i != 0){
            cout << " \t Ma nem volt sugárzás"<< endl;
                noRadDays++;
        }
        


        cout<<"\t Az élő növények: " << endl;
        int deltaReq = 0;
        int alphaReq = 0;
        int living = 0;
        for (size_t  j = 0;  j < plants.size(); j++)
        {
            if (plants[j]->isAlive())
                {
                    cout<< "\t \t" << plants[j]->getName() << " " << plants[j]->getType() << " " << plants[j]->getNutrient()<< endl;
                    deltaReq+= plants[j]->DeltaReq();
                    alphaReq+= plants[j]->AlphaReq();
                    living++;
                }
        }
        if(living == 0){
            //cout<<"\t"<<"Minden növény meghalt vége a megfigyelésnek a " << i <<"-dik napon"<< endl;
            i = days + 1;
            break;
        }
        diff = alphaReq - deltaReq;
        if(diff >= 3){
           // cout<<"\t Alfa sugárzás lesz holnap" << endl;
            for (size_t  j = 0; j < plants.size(); j++)
            {
                if (plants[j]->isAlive()) {plants[j]->AlphaIn();}
            }
        }
        else if(diff <= -3 ){
            //cout<<"\t Delta sugárzás lesz holnap" <<endl; 
            for (size_t  j = 0; j < plants.size(); j++)
            {
                if (plants[j]->isAlive()){plants[j]->DeltaIn();}
            }
        }
        else{
            //cout<<"\t Nem lesz holnap sugárzás" <<endl;
            for (size_t  j = 0; j < plants.size(); j++)
            {
                if (plants[j]->isAlive()) {plants[j]->noRadIn();}
            }
        }
        if(noRadDays == 2){
            cout<<"Nem volt sugárzás 2 nap egymás után a megfigyelésnek vége"<< endl;
            i = days + 1;
            break;
        }
        if(noRadDays != 2 && living  > 0 && i == days){
            cout<< "Végig érünk a megigyelés időtartamával"<< endl;
        }
    }
}

Planet::~Planet(){
    for (Plant* p : plants)
    {
        delete p;
    }
    
}