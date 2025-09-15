import java.util.ArrayList;

public class ArrayListDemo {
    public static ArrayList<String> getStrSameBeginningAndEnding(ArrayList<String>  strings){
            ArrayList<String> result = new ArrayList<String>();
        
        for(String str : strings){
            if(str.length() > 0 && str.charAt(0) == str.charAt(str.length() - 1)){
                result.add(str);
            }
        }


        return result;
    }

    public static void  removeStrDifferBeginningAndEnding(ArrayList<String> strings){
        strings.removeIf(element -> (element.length() == 0 || element.charAt(0) != element.charAt(element.length() - 1 )) );
    }
    public static void main(String[] args) {
       // ArrayList<Integer> numebers = new ArrayList<Integer>();   
       ArrayList<String> strings1 = new ArrayList<String>();
       strings1.add("ada");
       strings1.add("java");
       strings1.add("");
       strings1.add("aaaaaa");
       System.out.println(strings1);


       ArrayList<String> strings2 = getStrSameBeginningAndEnding(strings1);
       System.out.println(strings2);

       removeStrDifferBeginningAndEnding(strings1);
       System.out.println(strings1);




    }
}
