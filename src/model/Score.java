package model;

public class Score {
    private String studentId;
    private String courseId;
    private double catMarks;   // Continuous Assessment Test marks
    private double examMarks;  // Final exam marks
    private double total;
    private String grade;
    private String semester;
    private String addedByRole; //"Admin"or "lecturer"

    // Constructor
    public Score(String studentId,
         String courseId,
          double catMarks,
           double examMarks,
            String semester,
        String addedByRole) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.catMarks = catMarks;
        this.examMarks = examMarks;
        this.semester = semester;
        this.addedByRole = addedByRole;
        calculateTotal();
        assignGrade();
    }

    // Getters
    public String getStudentId() {
         return studentId;
         }
    public String getCourseId() {
         return courseId; 
        }
    public double getCatMarks() { 
        return catMarks;
     }
    public double getExamMarks() { 
        return examMarks;
     }
    public double getTotal() { 
        return total; 
    }
    public String getGrade() {
         return grade;
         }
    public String getSemester() {
         return semester; 
        }
        public String getAddedByrole(){
            return addedByRole;
        }

    // Setters with validation
    public void setCatMarks(double catMarks) {
        if (catMarks < 0 || catMarks > 30) {
            throw new IllegalArgumentException("CAT marks must be between 0 and 30.");
        }
        this.catMarks = catMarks;
        calculateTotal();
        assignGrade();
    }

    public void setExamMarks(double examMarks) {
        if (examMarks < 0 || examMarks > 70) {
            throw new IllegalArgumentException("Exam marks must be between 0 and 70.");
        }
        this.examMarks = examMarks;
        calculateTotal();
        assignGrade();
    }

    public void setSemester(String semester) { this.semester = semester; }

    // Logic
    private void calculateTotal() {
        this.total = catMarks + examMarks;
    }

    private void assignGrade() {
        if (total >= 70) {
            grade = "A";
        } else if (total >= 60) {
            grade = "B";
        } else if (total >= 50) {
            grade = "C";
        } else if (total >= 40) {
            grade = "D";
        } else {
            grade = "F";
        }
    }
    public void setAddedByRole(String addedByRole) {
        this.addedByRole = addedByRole;
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", catMarks=" + catMarks +
                ", examMarks=" + examMarks +
                ", total=" + total +
                ", grade='" + grade + '\'' +
                ", semester='" + semester + '\'' +
                ",addedByRole='"+addedByRole+'\''+
                '}';
    }
}

