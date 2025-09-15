import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import auto.Auto;
import auto.utils.Color;

public class Main {
    public static void main(String[] args) {
        BufferedReader buffReader = null;
        try{
            File inFile = new File("input.txt");
            buffReader = new BufferedReader(new FileReader(inFile));
            Auto a = new Auto();
            
            String line;
            while( (line = buffReader.readLine()) != null ){
                String[] lineChunks = line.split(",");
                Color color = Color.BLUE;
                Auto temp = new Auto(lineChunks[0], color.valueOf(lineChunks[2]),Integer.parseInt(lineChunks[2]));
                if(a.isFaster(a,temp))
                {
                    System.out.println("the first is faster");

                }
                else{
                    System.out.println("the second is faster");
                }

            }
        }
        catch(FileNotFoundException e){
            System.out.println("No file");
        }
        catch(IOException e){
            System.out.println("No line in file");
        }
        finally{
            try{
            buffReader.close();
            }
            catch (IOException e){
                System.out.println("idk");
            }
        }
    }
}
