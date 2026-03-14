
// model/Person.java
public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String idNumber;      // national ID / reg number
    protected Role role;
    protected String username;

    public Person(int id, String firstName, String lastName, String email,
                  String phoneNumber, String idNumber, Role role, String username) {
        this.id          = id;
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.email       = email;
        this.phoneNumber = phoneNumber;
        this.idNumber    = idNumber;
        this.role        = role;
        this.username    = username;
    }

    // Getters
    public int    getId()          { return id; }
    public String getFirstName()   { return firstName; }
    public String getLastName()    { return lastName; }
    public String getFullName()    { return firstName + " " + lastName; }
    public String getEmail()       { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getIdNumber()    { return idNumber; }
    public Role   getRole()        { return role; }
    public String getUsername()    { return username; }

    // Setters
    public void setId(int id)                   { this.id = id; }
    public void setFirstName(String firstName)   { this.firstName = firstName; }
    public void setLastName(String lastName)     { this.lastName = lastName; }
    public void setEmail(String email)           { this.email = email; }
    public void setPhoneNumber(String p)         { this.phoneNumber = p; }
    public void setIdNumber(String idNumber)     { this.idNumber = idNumber; }
    public void setRole(Role role)               { this.role = role; }
    public void setUsername(String username)     { this.username = username; }

    public abstract String getPassword();
    public abstract void   setPassword(String password);
}
