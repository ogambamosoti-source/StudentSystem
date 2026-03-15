package model;

public class Lecturer implements Person{
    // Fields
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String idNumber;
    private String role;
    private String userName;
    private String password;
    private String staffId;

    // Constructor
    public Lecturer( String firstName,
         String lastName,
         String email,
         int phoneNumber,
          String idNumber,
          String role,
          String userName,
        String password,
    String staffId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.password = password;
        this.role = role;
        this.userName = userName;
        this.staffId = staffId;
    }
    // Implement Person interface methods
    @Override
    public String getFirstName() { return firstName; }

    @Override
    public String getLastName() { return lastName; }

    @Override
    public String getEmail() { return email; }

    @Override
    public int getPhoneNumber() { return phoneNumber; }

    @Override
    public String getRole(){ return role;}

    @Override
     public String getIdNumber(){ return idNumber;}
     @Override 
     public String getUserName(){ return userName;
     }
    public String getStaffId(){ return staffId;}
    public String getPassword() { return password; }
    
    // Setters (optional, depending on immutability preference)
    public void setFirstName(String firstName) {
         this.firstName = firstName;
         }
    public void setLastName(String lastName) {
         this.lastName = lastName; 
        }
    public void setEmail(String email) {
         this.email = email; 
        }
    public void setPhoneNumber(int phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    public void setIdNumber(String idNumber) { 
        this.idNumber = idNumber; 
    }
    public void setPassword(String password) {
         this.password = password; 
        }
        public void setStaffId(String staffId){
            this.staffId = staffId;
        }
 @Override
    public String toString() {
        return "Lecturer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ",staffId='"+ staffId + '\''+
                ",role='"+ role + '\''+
                '}';
    }
}
