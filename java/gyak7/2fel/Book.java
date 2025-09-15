
enum Genre {
    FANTASY, SATIRE, SCIFI, PHILOSOPHY, EDUCATIONAL;
}

public class Book {

    private String author;
    private String title;
    private int reservePrice;
    private static int staticId = 0;
    private int id;
    private Genre genre;

    Book(String _author, String _title, int _reservePrice,Genre _genre){
        this.author = _author;
        this.title = _title;
        this.reservePrice = _reservePrice;
        this.genre = _genre;
        this.id = staticId++;
    }

    void make(String _author, String _title,String _genre, int _reservePrice){
        if(_author != null && _title != null && _genre != null && _reservePrice > 0  ){

        }
    }




    boolean isSameGenre(Book a, Book b){
        if(b.genre == a.genre){
            return true;
        }
        else{
            return false;
        }

    }

}
