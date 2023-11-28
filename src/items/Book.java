package items;

public class Book extends Item {

    private final String isbnNo;
    private final String author;
    private final String genre;

    public Book( String isbnNo, String author, String genre, String title, String publication ) {
        super(title, publication);
        this.isbnNo = isbnNo;
        this.author = author;
        this.genre = genre;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbnNo='" + isbnNo + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                "} " + super.toString();
    }
}