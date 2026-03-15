package model;

public class Librarian implements Person{
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String idNumber;
    private String role;
    private String userName;
    private String staffId;
    private String password;

    public Librarian(String firstName,
        String lastName,
        String email,
        int phoneNumber,
        String idNumber,
        String role,
        String userName,
    String staffId,
String password){
this.role = role;
this.firstName = firstName;
this.lastName = lastName;
this.email = email; 
this.phoneNumber = phoneNumber;
this.idNumber = idNumber;
this.userName = userName;
this.staffId = staffId;
this.password = password;
    } 
    @Override
    public String getFirstName(){
        return firstName;
    }
    @Override
    public String getLastName(){
        return lastName;
    }
    @Override
    public String getEmail(){
        return email;
    }
    @Override
    public int getPhoneNumber(){
        return phoneNumber;
    }
    @Override
      public String getIdNumber(){
    return idNumber;
}
@Override
public String getUserName(){
    return userName;
}
public String getStaffId(){
    return staffId;
}
@Override
    public String getRole(){
        return "Librarian";
    }
public void setRole(String role){
    this.role = role;
}
    public String getPassword(){
        return password;
    }

   
    @Override
    public String toString() {
        return "LibraryAdmin{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", idNumber='" + getIdNumber() + '\'' +
                ", staffId='" + getStaffId() + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

