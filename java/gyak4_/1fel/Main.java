public class Main{
    public static void main(String[] args){

        /* 1.a fel
        double[] num = {1.3, 5.3, 7.7, -2.3, 23.45};
        double avr = 0.0;
        double diff = 24.0;
        
        for (int i = 0; i < 5; i++){
            avr += num[i];
        }  

        avr = avr/5.0;
        System.out.println("The avrage is: " + avr); 

        for(int i = 0; i <5; i++){
            if(Math.abs(avr - num[i]) < diff){
                diff = Math.abs(avr - num[i]);
            }
        }

        System.out.println("The smallest difference from avrage is: " + diff); 
        */

        System.out.println("how many numbers: ");

       int n = Integer.parseInt(System.console().readLine());
       
       double[] num = new double[n];
       double avr = 0;
       double diff = 0;
       
       for(int i = 0; i < n; i++){
           num[i] = Double.parseDouble(System.console().readLine());

       }

          for (int i = 0; i < n; i++){
            avr += num[i];
        }  

        avr = avr/n;
        System.out.println("The avrage is: " + avr); 

        for(int i = 0; i <n; i++){
            if(i == 0){
                diff = Math.abs(avr - num[i]); 
            }
            else if(Math.abs(avr - num[i]) < diff){
                diff = Math.abs(avr - num[i]);
            }
        }

        System.out.println("The smallest difference from avrage is: " + diff); 
        
        


    }

}