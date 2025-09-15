import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a concert. The concert has attendees, which is people who would
 * like to attend the concert. When the concert start, it notifies them,
 * when the concert ends, it notifies them again
 *
 * Koncertet reprezental. Vannak resztvevoi, akik a fesztivalozok, akik el
 * szeretnenek menni a koncertre. Amikor indul a koncert, ertesiti oket,
 * amikor vege a koncertnek, ujra ertesiti oket
 */
public class Concert {


    // Collection for people who would like to go to the concert
    // Adatszerkezet a fesztivalozoknak, akik reszt akarnak venni a koncerten
    private final List<Attendee> attendees = Collections.synchronizedList(new ArrayList<>());
    // How much time till the concert starts
    // Ennyi ido mulva kezd a koncert
    private final int startTime;
    // Duration of the concert
    // A koncert idotartama
    private final int duration;
    private final String name;

    public Concert(final String name, final int startTime, final int duration){
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
    }

    /**
     * Wait until startTime elapses, then notify attendees that the concert is starting.
     * Wait until duration elapses, then notify attendees that the concert is over
     *
     * Var, amig a startTime letelik, majd ertesiti a resztvevoket, hogy kezdodik a koncert.
     * Utana var, amig a duration letelik, majd ertesiti a resztvevoket, hogy vege a koncernek
     */
    public void start(){
        try {
            Thread.sleep(startTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (attendees) {
            for (Attendee attendee : attendees) {
                System.out.println(attendee.getName() + " is going to " + this.name);
                synchronized (attendee) {
                    attendee.notify();
                }

            }
        }

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (attendees) {
            for (Attendee attendee : attendees) {
                System.out.println(this.name + " finished, " + attendee.getName() + " is going to party");
                synchronized (attendee) {
                    attendee.notify();
                }

            }
        }
    }

    /**
     * Adds an attendee to the the attendees collection, meaning they would like to go to the concert
     *
     * Hozzaad egy fesztivalozot a resztvevokhoz, jelezve, hogy reszt szeretnenek venni a koncerten
     * @param attendee
     */
    public void addAttendee(final Attendee attendee){
        this.attendees.add(attendee);
    }

}
