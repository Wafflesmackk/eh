public class Book {
    private String author;
    private String title;
    protected int pages;


    public Book(){
        this.author = "John Steinbeck";
        this.title = "Of Mice an Men";
        this.pages = 107;
    }

    public Book(String author_, String title_, int pages_){
        if(author_.length() < 2 || title_.length() < 4){
            throw new IllegalArgumentException();
        }
        else{
            this.author = author_;
            this.title = title_;
            this.pages = pages_;
        }
    }

    public String getShorName(){
        return this.author.substring(0,2) + this.title.substring(0, 4) + this.pages;
    }

    public String toString(){
        return this.author + ": " + this.title + ": " + this.pages;
    }
    public String createReference(String article, int start, int end){
        return this.getShorName() + start + " - " + end +"referenced in article: " + article;
    }

    public int getPages(){
        return this.pages;
    }
}
