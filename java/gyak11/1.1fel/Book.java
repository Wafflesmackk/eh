public class Book implements Printable {
    private String author;
    private String title;
    private int pages;

    public Book(String author_, String title_, int pages_){
        if(author_.length() < 2 || title_.length() < 4 || pages_ < 0){
            throw new IllegalArgumentException();
        }
        this.title = title_;
        this.author = author_;
        this.pages = pages_;
    }

    @Override
    public String toString(){
        return this.author + ": " + this.title;
    }

    public String getShortName(){
        return this.author.substring(0,1) + ": " + this.title.substring(0,4);
    }
    public void print(){
        System.out.println(this.toString());
    };
    

    abstract public int getPrice();
    abstract String createReference(String article, int from, int to);
}
