import java.io.*;

public class Hello {
    public static void main(String[] args){ 
        BufferedReader buffReader = null;
        String idk;
        try{
            File inFile = new File(args[0]);
            buffReader = new BufferedReader(new FileReader(inFile));
            idk= System.console().readLine();

            String line;
            int con = 0;
            while( (line = buffReader.readLine()) != null ){
                if(line.contains(idk)){
                    con++;
                }


            }
            System.out.println("contains " + con + " them");
        }
        catch(FileNotFoundException e){
            System.out.println("No file?");
        }
        catch(IOException e){
            System.out.println("No line in file?");
        }
        catch(NullPointerException e){
            System.out.println("No arguments?");
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
