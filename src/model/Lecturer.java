package model;

public class Lecturer implements Person{
    // Fields
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int idNumber;
    private String role;
    private String password;
    private String staffId;

    // Constructor
    public Lecturer( String firstName,
         String lastName,
         String email,
         int phoneNumber,
          int idNumber,
          String role,
        String password,
    String staffId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.password = password;
        this.role = role;
        this.staffId = staffId;
    }
       public Lecturer(int int1, String string, String string2, String string3, int int2, String string4) {
        //TODO Auto-generated constructor stub
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
    @Override public int getIdNumber(){ return idNumber;}
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
    public void setIdNumber(int idNumber) { 
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
