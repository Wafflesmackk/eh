package person;


public class Person {
 
    
    public String foreName;
    public String surName;
    public String job;
    public Gender gender;
    public int age;

    public Person(String foreName_, String surName_, String job_, Gender gender_, int age_){
        this.foreName = foreName_;
        this.surName = surName_;
        this.job = job_;
        this.gender = gender_;
        this.age = age_;
    }

    public String toString() {
        return "name: " + this.foreName + " " + this.surName + "gender: " + this.gender +" working as a(n):" + this.job + " aged: " + this.age;
    }

    public boolean equals(Person p){
        if(this.foreName == p.foreName && this.surName == p.surName && this.job == p.job && this.gender == p.gender && this.age == p.age){

            return true;
        }
        else{
            return false;
        }

    }
}
