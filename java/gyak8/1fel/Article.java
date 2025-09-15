import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter; 

public class Article {
    private String title;
    private String body;
    private String conclusion;
    private ArrayList<Book> refs = new ArrayList<Book>(); 

    public Article(){
        this.title = "";
        this.body = "";
        this.conclusion = "";
        this.refs = new ArrayList<Book>(); 
    }

    public Article(String title_, String body_, String conclusion_, ArrayList<Book> refs_){
        this.title = title_;
        this.body = body_;
        this.conclusion = conclusion_;
        this.refs = new ArrayList<Book>(refs_); 
    }

    public void addBookToReferences(Book otherBook){
        this.refs.add(otherBook);
    }

    public void print(String filename){
        try{

            PrintWriter writer = new PrintWriter(filename,"UTF-8");
            writer.println(this.title + "\n");
            writer.println(this.body + "\n");
            writer.println(this.conclusion + "\n");
            for(int i = 0; i< refs.size(); i++){
                writer.println(this.refs.get(i).createReference(this.title,1,this.refs.get(i).getPages()) + "\n");
            }
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

}
