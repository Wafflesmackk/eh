abstract class Animal {
    abstract public void noise();
    public void move(){
        System.out.println("the animal moved");
    }
}

class Cat extends Animal{
    @Override
    public void noise(){
        System.out.println("meow");
    }
}

public class AbstractClassDemo{
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        cat1.move();
    }
}