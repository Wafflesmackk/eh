public class Main {

    public static void main(String[] args){
        double[] vectorV = {1,2,3,4,5,6};
        double[] vectorU = {5,6,7,8,8,10};

        DoubleVector v = new DoubleVector(vectorV);
        DoubleVector u = new DoubleVector(vectorU);

        System.out.println("vector v is: " + v.toString() + "\nvector u is: " + u.toString());
        System.out.println("the dot product is: " + v.dotProduct(u));
        System.out.println("the difference is: "+ v.difVector(u));
        System.out.println("the sum is: " + v.sumVector(u));
        System.out.println("v vector multiplied by 3 is: " + v.scalarProd(2.0));

    }
}
