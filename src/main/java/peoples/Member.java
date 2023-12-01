package peoples;

import items.Book;

import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

public class Member extends Person implements IMember {

    private final int libraryCardId;
    private TreeSet<Book> issuedBooks = new TreeSet<>();

    public Member(String firstname, String lastname, String email, String phoneNo) {
        super(firstname, lastname, email, phoneNo);
        this.libraryCardId = new Random().nextInt(99999 - 10000) + 10000;
    }

    public int getLibraryCardId() {
        return libraryCardId;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNo() {
        return phoneNo;
    }

    @Override public void addIssuedBook(Book book) {
        issuedBooks.add(book);
    }

    public TreeSet<Book> getIssuedBooks() {
        return issuedBooks;
    }

    @Override public int getIssuedBooksCount() {
        return issuedBooks.size();
    }

    @Override public void removeIssuedBook(Book book) {
        issuedBooks.remove(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(getLibraryCardId(), member.getLibraryCardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLibraryCardId());
    }

    @Override
    public String toString() {
        return "Member{" +
                "libraryCardId=" + libraryCardId +
                ", issuedBook=" + issuedBooks +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}