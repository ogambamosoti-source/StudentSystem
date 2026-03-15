package service;

import model.*;
import dao.*;
import java.util.List;

public class StudentService {
    private StudentDao studentDao;
    private CourseDao courseDao;
    private ProgramDao programDao;

    public StudentService(StudentDao studentDao, CourseDao courseDao, ProgramDao programDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.programDao = programDao;
    }

    // Student operations
    public boolean addStudent(Student student) {
        try {
            studentDao.addStudent(student);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public Student getStudentByIdNumber(String idNumber) {
        try {
            return studentDao.getStudentByIdNumber(idNumber);
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
            return null;
        }
    }

    public boolean updateStudent(Student student) {
        try {
            studentDao.updateStudent(student);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        try {
            studentDao.deleteStudent(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> listStudents() {
        try {
            return studentDao.getAllStudents();
        } catch (Exception e) {
            System.out.println("Error listing students: " + e.getMessage());
            return null;
        }
    }

    // Course operations (for students)
    public List<Course> listCourses() {
        try {
            return courseDao.listCourses();
        } catch (Exception e) {
            System.out.println("Error listing courses: " + e.getMessage());
            return null;
        }
    }

    public Course getCourseByName(String name) {
        try {
            return courseDao.getCourseByName(name);
        } catch (Exception e) {
            System.out.println("Error fetching course: " + e.getMessage());
            return null;
        }
    }

    // Program operations (for students)
    public List<Program> listPrograms() {
        try {
            return programDao.getAllPrograms();
        } catch (Exception e) {
            System.out.println("Error listing programs: " + e.getMessage());
            return null;
        }
    }

    public Program getProgramById(int id) {
        try {
            return programDao.getProgramById(id);
        } catch (Exception e) {
            System.out.println("Error fetching program: " + e.getMessage());
            return null;
        }
    }
}
