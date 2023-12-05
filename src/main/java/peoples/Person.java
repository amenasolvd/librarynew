package peoples;

public abstract class Person {
    public String firstname;
    public String lastname;
    public String email;
    public String phoneNo;

    public Person(String firstname, String lastname, String email, String phoneNo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public abstract String getFirstname();
    public abstract void setFirstname(String firstname);
    public abstract String getLastname();
    public abstract void setLastname(String lastname);
    public abstract String getEmail();
    public abstract void setEmail(String email);
    public abstract String getPhoneNo();
}