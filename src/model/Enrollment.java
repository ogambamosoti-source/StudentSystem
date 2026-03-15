package model;

import java.time.LocalDate;

public class Enrollment {
    private int studentId;
    private int programId;
    private LocalDate enrollmentDate;
    private String status; // e.g., "Active", "Completed", "Dropped"

    public Enrollment(int studentId,int programId, LocalDate enrollmentDate, String status) {
        this.studentId = studentId;
        this.programId = programId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public int getProgramById() {
        return programId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    // Utility method
    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + student.getStudentId() +
                ", course=" + program.getProgramId() +
                ", enrollmentDate=" + enrollmentDate +
                ", status='" + status + '\'' +
                '}';
    }
}
