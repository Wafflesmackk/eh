

public class Main {
    public static void main(String[] args) {
    
        City myCity = City.BUDAPEST;

        System.out.println(myCity.toString());

        City values[] = City.values();
        for (City value : values){
            System.out.println(value.toString());
        }

        WeekDay day = WeekDay.SUN;
        System.out.println(day.efficentNextDay(-5).toString());
    }
}