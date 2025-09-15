public class Main{
    public static void main(String[] args){
        int[] linearData1 = {1,2,3,4,5,6};

        IntegerMatrix matrix = new IntegerMatrix(2,3,linearData1);

        System.out.println(matrix.toString());
    }
}
