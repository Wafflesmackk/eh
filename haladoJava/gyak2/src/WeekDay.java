import java.util.List;

public enum WeekDay {
    MON(new String[]{"hu: Hétfő","ge: Montag"}),
    TUE(new String[]{"en: Tuesday","ge: Dienstag"}),
    WED(new String[]{"hu: Szerda","en: Wednesday"}),
    THU(new String[]{"en: Thursday","Csütörtök"}),
    FRI(new String[]{"ge: Freitag","hu: Péntek"}),
    SAT(new String[]{"hu: Szombat","en: Saturday"}),
    SUN(new String[]{"hu: Vasárnap","ge: Sonntag"});


    private static String[] lang = {"en", "hu","ge"};

    private String[] names;

    WeekDay(String[] names){
        this.names = names;
    }

    public WeekDay nextDay(){
        if(this.ordinal() + 1 == 7){
            return WeekDay.MON;
        }
        else{
            return WeekDay.values()[this.ordinal()+1];
        }
    }

    public WeekDay nextDay(int steps){
        WeekDay endDay = this;
        if(steps < 0 ){
            steps = steps + 7;
        }

        for (int i = 0; i < steps; i++){
            endDay = endDay.nextDay();
        }
        return endDay;
    }

    public WeekDay efficentNextDay(int steps){
        WeekDay endDay = this;
        steps = steps % 7;

        for (int i = 0; i < steps; i++){
            endDay = endDay.nextDay();
        }
        return endDay;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
