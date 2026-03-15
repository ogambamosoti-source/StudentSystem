package service;

import model.*;
import dao.*;

import java.util.List;

public class AdminService {
    private final StudentDao studentDao;
    private final LecturerDao lecturerDao;
    private final ProgramDao programDao;
    private final CourseDao courseDao;

    public AdminService(StudentDao studentDao, LecturerDao lecturerDao,
                        ProgramDao programDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.lecturerDao = lecturerDao;
        this.programDao = programDao;
        this.courseDao = courseDao;
    }

    // Student operations
    public void addStudent(Student student) {
        try {
            studentDao.addStudent(student);
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public List<Student> listStudents() {
        try {
            return studentDao.listStudents();
        } catch (Exception e) {
            System.err.println("Error listing students: " + e.getMessage());
            return List.of(); // return empty list if error occurs
        }
    }

    public void deleteStudent(int id) {
        try {
            studentDao.deleteStudent(id);
            System.out.println("Student deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    // Lecturer operations
    public void addLecturer(Lecturer lecturer) {
        try {
            lecturerDao.addLecturer(lecturer);
            System.out.println("Lecturer added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding lecturer: " + e.getMessage());
        }
    }

    public List<Lecturer> listLecturers() {
        try {
            return lecturerDao.listLecturers();
        } catch (Exception e) {
            System.err.println("Error listing lecturers: " + e.getMessage());
            return List.of();
        }
    }

    public void deleteLecturer(int id) {
        try {
            lecturerDao.deleteLecturer(id);
            System.out.println("Lecturer deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting lecturer: " + e.getMessage());
        }
    }

    // Program operations
    public void addProgram(Program program) {
        try {
            programDao.addProgram(program);
            System.out.println("Program added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding program: " + e.getMessage());
        }
    }

    public List<Program> listPrograms() {
        try {
            return programDao.listPrograms();
        } catch (Exception e) {
            System.err.println("Error listing programs: " + e.getMessage());
            return List.of();
        }
    }

    public void deleteProgram(int id) {
        try {
            programDao.deleteProgram(id);
            System.out.println("Program deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting program: " + e.getMessage());
        }
    }

    // Course operations
    public void addCourse(Course course) {
        try {
            courseDao.addCourse(course);
            System.out.println("Course added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    public List<Course> listCourses() {
        try {
            return courseDao.listCourses();
        } catch (Exception e) {
            System.err.println("Error listing courses: " + e.getMessage());
            return List.of();
        }
    }

    public void deleteCourse(int id) {
        try {
            courseDao.deleteCourse(id);
            System.out.println("Course deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }
}
