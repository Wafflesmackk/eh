package main;


import person.Person;
import person.Gender;


public class Main {
    public static void main(String[] args){
        Person per1 = new Person("jon", "dough", "office worker", Gender.Male, 31);
        Person per2 = new Person("jon", "dough", "office worker", Gender.Male, 31);

        if(per1.equals(per2)){
            System.out.println("they are the same person");
        }
        else{
            System.out.println("they are different people");
        }
    }
    
}
