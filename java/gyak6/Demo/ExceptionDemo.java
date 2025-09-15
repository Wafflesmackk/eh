public class ExceptionDemo {
    public static void main(String[] args){
        try{
            if(Double.parseDouble(args[1]) == 0){
                throw new ArithmeticException("osztás u dumbass");
            }
            double myNum = Double.parseDouble(args[0]) / Double.parseDouble(args[1]);
            System.out.println(myNum);
        }
        catch(ArithmeticException e){
            System.out.println("kekw 0 osztas kekw");
        }
        catch(NumberFormatException e){
            System.out.println("Szamot bitte");
        }
        finally{
            System.out.println("bye");
        }
    }
    
}
