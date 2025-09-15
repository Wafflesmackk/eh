import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Sum {
    public static void main(String[] args){
        BufferedReader buffReader = null;
        BufferedWriter buffWriter = null;
        try{
            File inFile = new File("in.txt");
            //FileReader freader = new FileReader(inFile);
            buffReader = new BufferedReader(new FileReader(inFile));
            buffWriter = new BufferedWriter(new FileWriter("out.txt"));
            
            String line;
            while( (line = buffReader.readLine()) != null ){
                String[] lineChunks = line.split(",");
                int sum = 0;
                for(String alphanum : lineChunks){
                    sum += Integer.parseInt(alphanum);
                }
                buffWriter.write(String.valueOf(sum), 0,String.valueOf(sum).length());
                buffWriter.newLine();

                System.out.println(sum);

            }
        }
        catch(FileNotFoundException e){
            System.out.println("No file?");
        }
        catch(IOException e){
            System.out.println("No line in file?");
        }
        catch(NumberFormatException e){
            System.out.println("Not only numbers?");
        }
        finally{
            try{
            buffReader.close();
            buffWriter.close();
            }
            catch (IOException e){
                System.out.println("idk");
            }
        }
    }
}
