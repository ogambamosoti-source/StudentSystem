package model;  

public class Admin implements Person{
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String idNumber; 
    private String role;
    private String userName;
private String staffId;
private String password;
public Admin(String firstName,
    String lastName,
    String email,
    int phoneNumber,
    String idNumber,
    String role,
    String userName,
    String staffId,
    String password){
   this.firstName = firstName;
   this.lastName = lastName;
   this.email = email;
   this.phoneNumber = phoneNumber;
   this.idNumber = idNumber;
   this.role = role;
   this.userName = userName;
    this.staffId = staffId;
    this.password = password;
}
//getters and setters 
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
public String getRole(){
    return role;
}
@Override
public String getUserName(){
    return userName;
}
public String getStaffId(){
    return staffId;
}
public String getPassword(){
    return password;
}
public void setStaffId(String staffId){
    this.staffId = staffId;
}
public void setPassword(String password){
    this.password = password;
}

    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", staffId='" + staffId + '\'' +
                '}';
    }
}
