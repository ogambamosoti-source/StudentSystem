package model;

public class CourseOffering {
    private int id;
    private String courseCode;
    private String courseName;
    private String semester;
    private int capacity;

    // Constructors
    public CourseOffering(int id, String courseCode, String courseName, String semester, int capacity) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.semester = semester;
        this.capacity = capacity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
