public class PointMain{
    public static void main(String[] args){
        /*
        int x1 = Integer.parseInt(System.console().readLine());
        int y1 = Integer.parseInt(System.console().readLine());
        int x2 = Integer.parseInt(System.console().readLine());
        int y2 = Integer.parseInt(System.console().readLine());
        int x3 = Integer.parseInt(System.console().readLine());
        int y3 = Integer.parseInt(System.console().readLine());

        Point p1 = new Point(x1,y1);
        Point p2 = new Point(x2,y2);
        Point p3 = new Point(x3,y3);
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        */
        int x = 0;
        int y = 0;

        Point[] arr = new Point[3];

        for(int i = 0; i < 3; i++){
        x = Integer.parseInt(System.console().readLine());
        y = Integer.parseInt(System.console().readLine());
        arr[i] = new Point(x,y);
        System.out.println(arr[i].toString());
        } 


    }
}