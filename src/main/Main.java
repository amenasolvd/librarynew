package main;

import exceptions.PhoneNoNotValidException;
import items.Book;
import items.Item;
import items.Magazine;
import items.Newspaper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import peoples.Librarian;
import peoples.Manager;
import peoples.Member;
import peoples.Staff;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(main.Main.class);

    public static void main(String[] args) {
        Member member = new Member("amena","kureshi","amena.k@gmail.com","1565622665");
        Staff[] staff = new Staff[2];
        Manager[] manager = new Manager[1];
        Librarian[] librarian = new Librarian[1];
        Item[] item = new Item[5];
        Book book1 = new Book("12345","Khalid Hussaini","Fiction","A thousand Splendid Suns","" );
        Book book2 = new Book("12345","Khalid Hussini","Fiction","A thousand Suns", "");
        Magazine[] magazine = new Magazine[2];
        Newspaper[] newspaper = new Newspaper[1];
        Library library = new Library();
        try {
            library.addMember(member);
        } catch (PhoneNoNotValidException e) {
            LOGGER.info("This is not a valid Phone number");
        }
        library.addBook(book1);
        library.addBook(book2);
        Library.printAllBookInfo();
        Library.printAllMemberInfo();
        Library.printLibraryInfo();
    }
}