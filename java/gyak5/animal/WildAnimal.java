public enum WildAnimal{
    MONKEY("bananas", 10),
    ELEPHANT("raspberries",100),
    GIRAFFE("apples",15),
    RACOON("walnuts",20);

    public String food;
    public int amount;

    WildAnimal(String food_, int amount_){
        this.food = food_;
        this.amount = amount_;
    }
    public String toString(){
        return "A(n) " + this.name() + " likes " + this.food + " and eats " + this.amount +" a day.";
    }

    public static String listAllAnimals(){
        StringBuilder strBuilder = new StringBuilder();
        for (WildAnimal animal : WildAnimal.values()) {
            strBuilder.append(animal.ordinal() + ": ");
            strBuilder.append(animal.name() + " ");
            strBuilder.append("likes to eat ");
            strBuilder.append(animal.amount * 7);  //number of days in a week
            strBuilder.append(" " + animal.food + " a week \n");
    
        }   
        return strBuilder.toString();
    }

}