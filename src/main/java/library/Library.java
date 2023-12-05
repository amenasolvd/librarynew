package library;

import exceptions.*;
import items.*;
import linkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import peoples.*;

import java.time.Instant;
import java.util.*;

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
    public boolean reissue(Member member, Book book) {
        try {
            issue(member, book);
            return true;
        } catch (BorrowingBookLimitOverException e) {
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

    public static List<Book> searchBook(String bookTitle) throws NoBookFoundException {
        List<Book> searchResult = new ArrayList<>();
        for (Book i : bookList.getAll()) {
            if (i.getTitle().contains(bookTitle)) {
                searchResult.add(i);
            }
        }
        if (searchResult.isEmpty()) {
            throw new NoBookFoundException("No Book found");
        }
        return searchResult;
    }

    @Override
    public void addNewspaper(Newspaper newspaper) {
        try {
            newspaperList.add(newspaper);
            Date localDate = Date.from(Instant.now());
            if (newspaper.getPublishedDate().after(localDate)) {
                throw new InvalidInputException("Date is invalid");
            }
        } catch (InvalidInputException e) {
            LOGGER.error("Enter Date in correct format ");
        }
    }

    public static void printLibraryInfo() {
        LOGGER.info("Library Name: " + libraryName + '\'' +
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