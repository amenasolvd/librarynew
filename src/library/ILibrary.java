package library;

import exceptions.BorrowingBookLimitOverException;
import exceptions.NotAuthorizedException;
import exceptions.PhoneNoNotValidException;
import exceptions.ReissueNotValidException;
import items.Book;
import items.Newspaper;
import peoples.Member;
import peoples.Staff;

public interface ILibrary {

    void addBook(Book book);
    boolean deleteBook(Book book, Staff staff) throws NotAuthorizedException;
    void addMember(Member member) throws PhoneNoNotValidException;
    boolean deleteMember(Staff staff, Member member) throws NotAuthorizedException;
    boolean issue(Member member, Book book) throws BorrowingBookLimitOverException;
    boolean reissue(Member member, Book book) throws ReissueNotValidException;
    boolean returnBook(Member member, Book book);
    void addNewspaper(Newspaper newspaper);
}
