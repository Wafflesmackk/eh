public class Person{
    private String firstname;
    private String lastname;
    private int age;

    public Person(String firstname_, String lastname_, int age_){
        this.firstname = firstname_;
        this.lastname = lastname_;
        this.age = age_;
    }

    public String toString(){
        return this.firstname + " " + this.lastname + " " + this.age;
    }
}