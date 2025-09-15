public class PrintedBook extends Book {
    public Cover cover;

    public PrintedBook(){
        super();
        this.cover = cover.HARDCOVER;
        this.pages += 6;
    }
    public PrintedBook(String author_, String title_, int pages_, Cover cover_){
        super(author_,title_,pages_);
        this.cover = cover_;
        this.pages += 6;
    }
    public int getPrice(){
        if(this.cover == Cover.HARDCOVER){
            return this.pages * 3;
        }
        else{
            return this.pages * 2;
        }
    }
    @Override
    public String toString(){
        return super.toString() + ", " + this.cover;
    }

    public String CreateReference(String article, int start, int end){
        return super.toString() +"["+  start +"-"+ end + "]"+ "referenced in article" + article;
    }
    


}
