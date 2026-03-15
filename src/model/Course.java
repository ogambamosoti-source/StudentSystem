package model;

public class Course {
private String courseName;
private String courseCode;
private int creditHours;
private int departmentId;

public Course(String courseName,String courseCode,int creditHours,int depertmentId){
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.creditHours = creditHours;
    this.departmentId = depertmentId;
}

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    } 
    public int getDepartmentId(){
        return departmentId;
    }
    public void setDepartmentId(int departmentId){
        this.departmentId = departmentId;
    }

       @Override
    public String toString() {
        return "Course{" +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", creditHours=" + creditHours +
                ", departmentId= " + departmentId +
                '}';
    }

}

