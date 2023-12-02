package library;

import exceptions.PhoneNoNotValidException;
import items.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import peoples.Member;
import utils.ReadFile;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(library.Main.class);

    public static void main(String[] args) {
        Member member = new Member("amena", "kureshi", "amena.k@gmail.com", "1565622665");
        Book book1 = new Book("12345", "Khalid Hussaini", "Fiction", "A thousand Splendid Suns", "");
        Book book2 = new Book("12345", "Khalid Hussini", "Fiction", "A thousand Suns", "");
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
        try (Scanner sc = new Scanner(System.in)) {
            LOGGER.info("To Search book, Write title");
            String searchTitle = sc.nextLine();
            LOGGER.info(Library.searchBook(searchTitle));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReadFile readFile = new ReadFile();
        readFile.writeCountUniqueWords();
    }
}