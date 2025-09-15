package prog1.kotprog.dontstarve.solution;

import prog1.kotprog.dontstarve.solution.character.AIPlayer;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.Player;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.HumanPlayer;
import prog1.kotprog.dontstarve.solution.exceptions.NotImplementedException;
import prog1.kotprog.dontstarve.solution.inventory.items.*;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.FieldType;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.*;

/**
 * A játék működéséért felelős osztály.<br>
 * Az osztály a singleton tervezési mintát valósítja meg.
 */
public final class GameManager {
    /**
     * Az osztályból létrehozott egyetlen példány (nem lehet final).
     */
    private static GameManager instance = new GameManager();

    private static boolean isLoaded = false;
    private static boolean playerIn = false;

    private static boolean started = false;

    /**
     * Random objektum, amit a játék során használni lehet.
     */
    private final Random random = new Random();

    private ArrayList<Player> players;
    private Level level;

    /**
     * Az osztály privát konstruktora.
     */
    private GameManager() {
        players = new ArrayList<Player>();
    }

    /**
     * Az osztályból létrehozott példány elérésére szolgáló metódus.
     * @return az osztályból létrehozott példány
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * A létrehozott random objektum elérésére szolgáló metódus.
     * @return a létrehozott random objektum
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Egy karakter becsatlakozása a játékba.<br>
     * A becsatlakozásnak számos feltétele van:
     * <ul>
     *     <li>A pálya már be lett töltve</li>
     *     <li>A játék még nem kezdődött el</li>
     *     <li>Csak egyetlen emberi játékos lehet, a többi karaktert a gép irányítja</li>
     *     <li>A névnek egyedinek kell lennie</li>
     * </ul>
     * @param name a csatlakozni kívánt karakter neve
     * @param player igaz, ha emberi játékosról van szó; hamis egyébként
     * @return a karakter pozíciója a pályán, vagy (Integer.MAX_VALUE, Integer.MAX_VALUE) ha nem sikerült hozzáadni
     */
    public Position joinCharacter(String name, boolean player) {
        for(Player ply: players){
            if (ply.getName().equals(name)){
                return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        Position pos = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        if(!started && isLoaded) {
            ArrayList<Field> emptyFields = new ArrayList<>();
            for(Field field : level.getFields()) {
                if(field.getType() == FieldType.EMPTY){
                    emptyFields.add(field);
                }
            }

            Collections.shuffle(emptyFields);

            int distanceFromOthers = 50;
            boolean placed = false;
            while (!placed){
                boolean placable = true;
                for (Field field : emptyFields) {
                    for (Player ply : players) {
                        float plyX = ply.getCurrentPosition().getX();
                        float plyY = ply.getCurrentPosition().getY();
                        if (sqrt(pow((plyX - field.getX()), 2) + pow((plyY - field.getY()), 2)) < distanceFromOthers) {
                            placable = false;
                            break;
                        }
                    }
                    if(placable){
                        pos = new Position(field.getX(), field.getY() );
                        placed = true;
                        break;
                    }
                }
                distanceFromOthers = distanceFromOthers - 5;
                if(distanceFromOthers == 0){
                    return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
                }
            }

             ArrayList <AbstractItem> starterItems = new ArrayList<>();
            for(int i = 0; i< 4; i++){
                int randomItemId = random.nextInt(0,5);
                AbstractItem item = new ItemRawBerry(1);
                switch (randomItemId){
                    case (0):
                        item = new ItemTwig(1);
                        break;
                    case (1):
                        item = new ItemLog(1);
                        break;
                    case (2):
                        item = new ItemStone(1);
                        break;
                    case (3):
                        item = new ItemRawCarrot(1);
                        break;
                    case(4):
                        item = new ItemRawBerry(1);
                        break;
                }
                starterItems.add(item);
            }

            if (player && !playerIn) {
                HumanPlayer user = new HumanPlayer(name, pos);
                user.setItems(starterItems);
                user.organizeInventory();
                players.add(user);
                playerIn = true;
            }
            else if(!player){
                AIPlayer ai = new AIPlayer(name, pos);
                ai.setItems(starterItems);
                ai.organizeInventory();
                players.add(ai);
            }
            else{
                return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        return pos;
    }

    /**
     * Egy adott nevű karakter lekérésére szolgáló metódus.<br>
     * @param name A lekérdezni kívánt karakter neve
     * @return Az adott nevű karakter objektum, vagy null, ha már a karakter meghalt vagy nem is létezett
     */
    public BaseCharacter getCharacter(String name) {
        BaseCharacter character = null;
        for(Player ply : players){
            if(ply.getName() == name && ply.getHp() > 0){
                character = ply;
            }
        }
        return character;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy hány karakter van még életben.
     * @return Az életben lévő karakterek száma
     */
    public int remainingCharacters() {
        int count = 0;
        for(Player ply : players){
            if(ply.getHp() > 0){
                count++;
            }
        }
        return count;
    }

    /**
     * Ezen metódus segítségével történhet meg a pálya betöltése.<br>
     * A pálya betöltésének azelőtt kell megtörténnie, hogy akár 1 karakter is csatlakozott volna a játékhoz.<br>
     * A pálya egyetlen alkalommal tölthető be, később nem módosítható.
     * @param level_ a fájlból betöltött pálya
     */
    public void loadLevel(Level level_) {
        if(!isLoaded && players.size() == 0){
            this.level = level_;
            isLoaded = true;
        }
    }

    /**
     * A pálya egy adott pozícióján lévő mező lekérdezésére szolgáló metódus.
     * @param x a vízszintes (x) irányú koordináta
     * @param y a függőleges (y) irányú koordináta
     * @return az adott koordinátán lévő mező
     */
    public BaseField getField(int x, int y) {
        BaseField returnField = null;
        for(Field field : level.getFields()){
            if(field.getX() == x && field.getY() == y){
                returnField = field;
            }
        }
        return returnField;
    }

    /**
     * A játék megkezdésére szolgáló metódus.<br>
     * A játék csak akkor kezdhető el, ha legalább 2 karakter már a pályán van,
     * és közülük pontosan az egyik az emberi játékos által irányított karakter.
     * @return igaz, ha sikerült elkezdeni a játékot; hamis egyébként
     */
    public boolean startGame() {
        started = true;
        throw new NotImplementedException();
    }

    /**
     * Ez a metódus jelzi, hogy 1 időegység eltelt.<br>
     * A metódus először lekezeli a felhasználói inputot, majd a gépi ellenfelek cselekvését végzi el,
     * végül eltelik egy időegység.<br>
     * Csak akkor csinál bármit is, ha a játék már elkezdődött, de még nem fejeződött be.
     * @param action az emberi játékos által végrehajtani kívánt akció
     */
    public void tick(Action action) {
        throw new NotImplementedException();
    }

    /**
     * Ezen metódus segítségével lekérdezhető az aktuális időpillanat.<br>
     * A játék kezdetekor ez az érték 0 (tehát a legelső időpillanatban az idő 0),
     * majd minden eltelt időpillanat után 1-gyel növelődik.
     * @return az aktuális időpillanat
     */
    public int time() {
        throw new NotImplementedException();
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük a játék győztesét.<br>
     * Amennyiben a játéknak még nincs vége (vagy esetleg nincs győztes), akkor null-t ad vissza.
     * @return a győztes karakter vagy null
     */
    public BaseCharacter getWinner() {
        throw new NotImplementedException();
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék elkezdődött-e már.
     * @return igaz, ha a játék már elkezdődött; hamis egyébként
     */
    public boolean isGameStarted() {
        return started;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék befejeződött-e már.
     * @return igaz, ha a játék már befejeződött; hamis egyébként
     */
    public boolean isGameEnded() {
        throw new NotImplementedException();
    }

    /**
     * Ezen metódus segítségével beállítható, hogy a játékot tutorial módban szeretnénk-e elindítani.<br>
     * Alapértelmezetten (ha nem mondunk semmit) nem tutorial módban indul el a játék.<br>
     * Tutorial módban a gépi karakterek nem végeznek cselekvést, csak egy helyben állnak.<br>
     * A tutorial mód beállítása még a karakterek csatlakozása előtt történik.
     * @param tutorial igaz, amennyiben tutorial módot szeretnénk; hamis egyébként
     */
    public void setTutorial(boolean tutorial) {
        throw new NotImplementedException();
    }
}
