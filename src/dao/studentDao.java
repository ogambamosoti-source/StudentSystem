package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student implements Person {
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int idNumber;
    private String regNumber;
    private String password;
    private String role;
    private String userName;
    private Program program;//one to many:one program
    private List<Course> courses;//many to many: multiple courses to multiple students
    private Map<String,String> courseGrades;

    public Student(int phoneNumber,
                   String lastName,
                    String email,
                     String firstname,
                     int idNumber,
                      String password,
                      String role,
                      String userName,
                       String regNumber,
                       Program program) {
                        this.userName = userName;
       this.firstName = firstname;
       this.lastName = lastName;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.idNumber = idNumber;
       this.role = role;
       this.regNumber = regNumber;
       this.password = password;
       this.program = program;
this.courses = new ArrayList<>();
        this.courseGrades = new HashMap<>();
    }
    // GettersdNumber;


    // Implement Person interface
    @Override public String getFirstName() { return firstName; }
    @Override public String getLastName() { return lastName; }
    @Override public String getEmail() { return email; }
    @Override public int getPhoneNumber() { return phoneNumber; }
    @Override public String getUserName(){  return userName;}
    @Override public int getIdNumber(){ return idNumber;}
 public String getRegNumber() { return regNumber; }
    @Override public String getRole(){ return "Student";}
    public String getPassword() { 
        return password;
     } 
    
    public Program getProgram(){
        return program;
    }
    public void setProgram(Program program){
        this.program = program;
    }
    public List<Course> getCourses(){
        return courses;
    }
    public Map<String,String> getCourseGrades() {
        return courseGrades;
    }

    // Setters
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

    public void setPassWord(String password) {
        this.password = password;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
public void enrollInCourse(Course course){
    if(!courses.contains(course)){
        courses.add(course);
    }
}
    public void assignGrade(String courseCode,String courseGrade) {
        courseGrades.put(courseCode,"A");
    }
@Override
public String toString() {
    return "Student{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", regNumber='" + regNumber + '\'' +
            ",userName='"+userName + '\''+
            ",role='"+ role + '\''+
            ",program='"+ program + '\''+
            ", courseGrades='" + courseGrades + '\'' +
            
            '}';
