package peoples;

public abstract class Librarian extends Staff {

    public Librarian(String firstname, String lastname, String email, String phoneNo,
                     String designation, String department) {
        super(firstname, lastname, email, phoneNo, designation, department);
    }
}