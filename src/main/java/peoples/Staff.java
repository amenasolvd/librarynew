package peoples;

import java.util.Objects;

public class Staff extends Person {

    private int employeeID;
    private String designation;
    private String department;

    public Staff(String firstname, String lastname, String email, String phoneNo,
                 String designation, String department) {
        super(firstname, lastname, email, phoneNo);
        this.employeeID = (int) (Math.random() + 1000);
        this.designation = designation;
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;
        return getEmployeeID() == staff.getEmployeeID() && Objects.equals(getDesignation(), staff.getDesignation())
                && Objects.equals(department, staff.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeID(), getDesignation(), department);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "employeeID=" + employeeID +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                "} " + super.toString();
    }

}