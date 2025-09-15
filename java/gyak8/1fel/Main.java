public class Main {
    public static void main(String[] args) {
        Book book1 = new Book();
        System.out.println(book1.getShorName());
        try{
            Book book2 = new Book("a", "idkidk",50);
        }
        catch(IllegalArgumentException e){
            System.out.println("That is not correct (the arguments)");
        }
        Book book3 = new Book("J.R.R Toliken", "The Lord of the Rings", 12345);
        System.out.println(book3.getShorName());

        //-----------------------------------------------------------------
        PrintedBook book4 = new PrintedBook();
        System.out.println(book4.pages);
        System.out.println(book4.toString());



    }
}
