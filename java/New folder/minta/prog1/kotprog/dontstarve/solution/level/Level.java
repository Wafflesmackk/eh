package prog1.kotprog.dontstarve.solution.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A pályát leíró kép betöltéséért felelő osztály.
 */
public class Level {
    /**
     * A kép, ami a pályát tartalmazza.
     */
    private BufferedImage image;

    private ArrayList<Field> fields;

    /**
     * Konstruktor, amely a megadott fájlból beolvassa a pályát.
     * @param fileName a fájl, amely a pályát tartalmazza
     */
    public Level(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("A pálya beolvasása nem sikerült!");
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for(int x = 0; x< width; x++){
            for (int y = 0; y < height; y++){
                int currColor = getColor(x,y);
                Field field;
                switch (currColor) {
                    case(MapColors.BERRY):
                        field = new Field(x,y, FieldType.BERRY,1);
                        break;
                    case(MapColors.CARROT):
                        field = new Field(x,y,FieldType.CARROT,1);
                        break;
                    case (MapColors.TREE):
                        field = new Field(x,y,FieldType.TREE,4);
                        break;
                    case (MapColors.TWIG):
                        field = new Field(x,y,FieldType.TWIG,2);
                        break;
                    case (MapColors.STONE):
                        field = new Field(x,y,FieldType.STONE,5);
                        break;
                    case (MapColors.WATER):
                        field = new Field(x,y,FieldType.WATER, 0);
                        break;
                    default:
                        field = new Field(x,y,FieldType.EMPTY, 0);
                }
                fields.add(field);
            }
        }
    }

    public void addField(Field field){
        fields.add(field);
    }

    public Field getField(int x, int y){
        for(Field field : fields){
            if(field.getX() == x && field.getY() == y){
                return field;
            }
        }
        return null;
    }

    /**
     * A pálya magasságát lekérdező metódus.
     * @return a pálya magassága
     */
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * A pálya szélességét lekérdező metódus.
     * @return a pály szélessége
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * A pályát reprezentáló kép egy adott pixelének színét lekérdező metódus.<br>
     * @param x a képpont oszlopszáma
     * @param y a képpont sorszáma
     * @return a kép adott pozíciójának színe
     */
    public int getColor(int x, int y) {
        return image.getRGB(x, y);
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

}
