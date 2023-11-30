package library;

import exceptions.*;
import items.*;
import linkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import peoples.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Library implements ILibrary {

    private static final Logger LOGGER = LogManager.getLogger(library.Library.class);
    private static final String libraryName = "Billerica Public Library";
    private static final String website = "https://billericalibrary.org/";
    private static final String address = "15 Concord Rd, Billerica, MA 01821";
    private static final String phone = "(978) 671-0949";
    private static CustomLinkedList<Book> bookList = new CustomLinkedList<>();
    private static ArrayList<Magazine> magazineList = new ArrayList<>();
    private static CustomLinkedList<Member> memberList = new CustomLinkedList<>();
    private static ArrayList<Staff> staffList = new ArrayList<>();
    private static Set<Newspaper> newspaperList = new HashSet<>();

    public CustomLinkedList<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(CustomLinkedList<Member> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }

    public CustomLinkedList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(CustomLinkedList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Magazine> getMagazineList() {
        return magazineList;
    }

    public void setMagazineList(ArrayList<Magazine> magazineList) {
        this.magazineList = magazineList;
    }

    public Set<Newspaper> getNewspaper() {
        return newspaperList;
    }

    @Override
    public void addBook(Book book) {
        bookList.add(book);
    }

    @Override
    public boolean deleteBook(Book book, Staff staff) throws NotAuthorizedException {
        if (staff.getDesignation().equals("Manager")) {
            if (this.getBookList().contains(book)) {
                bookList.remove(book);
            }
            return true;
        }
        throw new NotAuthorizedException("You are not authorized to delete a book");
    }

    @Override
    public void addMember(Member member) throws PhoneNoNotValidException {
        if (member.getPhoneNo().length() == 10) {
            memberList.add(member);
            LOGGER.info("member added");
        } else {
            throw new PhoneNoNotValidException("This is not a valid Phone number");
        }
    }

    @Override
    public final boolean deleteMember(Staff staff, Member member) throws NotAuthorizedException {
        if (staff.getDesignation().equals("Manager")) {
            if (this.getMemberList().contains(member)) {
                getMemberList().remove(member);
            }
            return true;
        }
        throw new NotAuthorizedException("You are not authorized to delete member");
    }

    @Override
    public boolean issue(Member member, Book book) throws BorrowingBookLimitOverException {
        if (member.getIssuedBooksCount() >= 3) {
            throw new BorrowingBookLimitOverException("You can't issue more than three books");
        } else {
            member.addIssuedBook(book);
            bookList.remove(book);
            return true;
        }
    }

    @Override
    public boolean reissue(Member member, Book book) throws ReissueNotValidException {
        try {
            int reissueCount = 0;
            if (reissueCount < 1) {
                this.issue(member, book);
                reissueCount++;
                return true;
            }
            throw new ReissueNotValidException("Reissue is allowed only once");
        } catch (BorrowingBookLimitOverException | ReissueNotValidException e) {
            LOGGER.error("Your are exceeding borrowing book limit");
            return false;
        }
    }

    @Override
    public boolean returnBook(Member member, Book book) {
        if (member.getIssuedBooks().contains(book)) {
            bookList.add(book);
            member.removeIssuedBook(book);
            return true;
        }
        return false;
    }

    public static void searchBook() {
        try (Scanner sc = new Scanner(System.in)) {
            LOGGER.info("search by title");
            boolean allFound = false;
            String searchTitle = sc.nextLine();                         //This may throw exception if user don't put input
            for (Book i : bookList.getAll()) {
                boolean isFound = i.getTitle().contains(searchTitle);
                if (isFound) {
                    LOGGER.info("Search result:  " + i);
                    allFound = true;
                }
            }
            if (!allFound) {
                LOGGER.info("no Book found");
            }

        } catch (Exception e) {
            LOGGER.info("Give valid search input");
        }
    }

    @Override
    public void addNewspaper(Newspaper newspaper) {
        try {
            Scanner sc = new Scanner(System.in);
            LOGGER.info("Enter name of newspaper");
            String newspaperTitle = sc.nextLine();
            LOGGER.info("Enter published of newspaper in yyyy/mm/dd format");
            String newsDate = sc.next();                                            //This might throw Exception
            LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(newsDate);
            LocalDate date = LocalDate.parse(newsDate);
            if (date.isAfter(localDate)) {
                throw new InvalidInputException("Date is invalid");
            }
            newspaperList.add(newspaper);
        } catch (InvalidInputException e) {
            LOGGER.error("Enter Date in correct format ");
        }
    }

    public static void printLibraryInfo() {
        System.out.println("Library Name: " + libraryName + '\'' +
                "Library address: " + address + '\'' +
                "Phone Number: " + phone + '\'' +
                "Website: " + website + '\'');
    }

    public static void printAllBookInfo() {
        for (Book i : bookList.getAll()) {
            LOGGER.info("BookList : " + i);
        }
    }

    public static void printAllMemberInfo() {
        for (Member i : memberList.getAll()) {
            LOGGER.info("MemberList : " + i);
        }
    }
}