public class EBook extends Book {
    public int PDFsize;

    public EBook(String author_, String title_, int pages_,int PDFsize_){
        super(author_, title_, pages_);
        this.PDFsize = PDFsize_;
    }

    public int getPrice(){
        return this.PDFsize + this.pages;
    }

    public String CreateReference(String article, int start, int end){
        return super.toString() + "(" +  this.PDFsize + ")" + "[" + start + "-" + end + "]" + "referenced in article: " + article; 
    }
    public String CreateReference(String article, int date){
        return super.toString() + "(" +  this.PDFsize + ")" + "referenced in article: " + article + "accessing PDf date: " + date; 
    }
}
