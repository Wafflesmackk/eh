import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FestivalSimulator {

    // Time allocated to concerts
    // Koncertekre szant ido
    private static final int CONCERTS_DURATION_MSEC = 6000;
    // Time allocated for party/getting home
    // Bulizasra es hazamenetelre szant ido
    private static final int PARTY_DURATION_MSEC = 8000;
    // If needed...
    // Ha kell...
    private static final int NUMBER_OF_ADDITIONAL_THREADS = 1;

    // List of the concerts
    // Koncertek listaja
    private static final List<Concert> concerts = List.of(
            new Concert("Krubi", 2500, 1000),
            new Concert("Wellhello", 2000, 1000),
            new Concert("Halott Penz", 1500, 1000),
            new Concert("Tankcsapda", 2000, 1000),
            new Concert("Byealex", 1500, 1000),
            new Concert("Majka", 2500, 1000)
    );

    // List of attendees
    // Fesztivalozok listaja
    private static final List<Attendee> attendees = List.of(
            new Attendee("Olga"),
            new Attendee("Helga"),
            new Attendee("Eszter"),
            new Attendee("Feri"),
            new Attendee("Peti"),
            new Attendee("Laci")
    );

    private static ExecutorService executorService =
            Executors.newFixedThreadPool(concerts.size() + attendees.size() + NUMBER_OF_ADDITIONAL_THREADS);

    /**
     * 1. Invoke setup() method to assign attendees to concerts
     * 1. Hivjuk meg a setup() metodust, hogy hozzarendeljuk a fesztivalozokat a koncertekhez
     *
     * 2. Invoke the attendees' waitForConcert() method to start waiting for their concert
     *      (on a separate thread)
     * 2. Hivjuk meg a fesztivalozok waitForConcert() metodusat, hogy varakozzanak a koncertjukre
     *      (egy kulon szalon)
     *
     * 3. Invoke the concerts' start() method to get ready to perform
     *      (on a separate thread)
     * 3. Hivjuk meg a koncertek start() metodusat, hogy elkeszuljenek
     *      (egy kulon szalon)
     *
     * 4. Wait $CONCERT_DURATION_MSEC
     * 4. Varjunk $CONCERT_DURATION_MSEC-et
     *
     * ONLY Part 2./CSAK A 2. Resz:
     * 5a. After $CONCERT_DURATION_MSEC let the attendees know that the festival is over
     * 5a. $CONCERT_DURATION_MSEC utan adjuk a resztvevok tudtara, hogy vege a fesztivalnak
     *
     * ONLY Part 3./CSAK A 3. Resz:
     * 5b. Start the bus via the startBus() method (on a separate thread)
     * 5b. Inditsuk el a buszt a startBus() metodussal (egy kulon szalon)
     *
     * 6. Make any necessary adjustment so that the program terminates after
     *      $PARTY_DURATION_MSEC at the longest, but possible before, if it can
     * 6. Fejezzuk be ugy a kodot, hogy a program maximum $PARTY_DURATION_MSEC
     *      ido utan terminaljon, de ha lehetseges, ne varjuk ki mindenkepp ezt az idot
     * @param args
     */
    public static void main(String[] args) {
        setup();
        for(Attendee attendee : attendees){
            executorService.submit(() -> attendee.waitForConcert());
        }
        for(Concert concert : concerts){
            executorService.submit(() -> concert.start());
        }

        try {
            Thread.sleep(CONCERTS_DURATION_MSEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ONLY Part 2 / CSAK 2. Resz
        attendees.forEach(Attendee::setFestivalOver);

        // ONLY Part 3 / CSAK 3. Resz
        executorService.submit(() -> Bus.getInstance().startBus());

        try {
            executorService.shutdown();
            executorService.awaitTermination(PARTY_DURATION_MSEC, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }


    }

    /**
     * Sophisticated algorithm to assign people to concerts
     *
     * Szofisztikalt algoritmus, ami hozzarendeli a fesztivalozokat a koncertekhez
     */
    private static void setup(){
        for(int i = 0; i < concerts.size(); ++i){
            concerts.get(i).addAttendee(attendees.get(i));
        }


    }
}
