package service;

import model.*;
import dao.*;

import java.util.List;

public class LecturerService {
    private LecturerDao lecturerDao;
    private ScoreDao scoreDao;
    private StudentDao studentDao;
    private BookDao bookDao;

    public LecturerService(LecturerDao lecturerDao, ScoreDao scoreDao, StudentDao studentDao, BookDao bookDao) {
        this.lecturerDao = lecturerDao;
        this.scoreDao = scoreDao;
        this.studentDao = studentDao;
        this.bookDao = bookDao;
    }

    // Lecturer CRUD
    public boolean addLecturer(Lecturer lecturer) {
        try {
            lecturerDao.addLecturer(lecturer);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding lecturer: " + e.getMessage());
            return false;
        }
    }

    public List<Lecturer> listLecturers() {
        try {
            return lecturerDao.listLecturers();
        } catch (Exception e) {
            System.out.println("Error listing lecturers: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteLecturer(int id) {
        try {
            lecturerDao.deleteLecturer(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting lecturer: " + e.getMessage());
            return false;
        }
    }

    public boolean updateLecturer(Lecturer lecturer) {
        try {
            lecturerDao.updateLecturer(lecturer);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating lecturer: " + e.getMessage());
            return false;
        }
    }

    // Lecturer assigns grade to a student in a course
    public boolean assignGrade(Lecturer lecturer, Student student, Course course, double score, String letterGrade) {
        try {
            Score grade = new Score(0, student.getId(), course.getId(), score, letterGrade);
            scoreDao.addScore(grade);
            return true;
        } catch (Exception e) {
            System.out.println("Error assigning grade: " + e.getMessage());
            return false;
        }
    }

    // Lecturer assigns a course
    public boolean assignCourse(Lecturer lecturer, Course course) {
        try {
            lecturer.getCoursesTaught().add(course.getId());
            lecturerDao.updateLecturer(lecturer);
            return true;
        } catch (Exception e) {
            System.out.println("Error assigning course: " + e.getMessage());
            return false;
        }
    }

    // Lecturer borrows a book
    public boolean borrowBook(Lecturer lecturer, int bookId) {
        try {
            bookDao.borrowBook(lecturer.getId(), bookId);
            return true;
        } catch (Exception e) {
            System.out.println("Error borrowing book: " + e.getMessage());
            return false;
        }
    }

    // Lecturer returns a book
    public boolean returnBook(Lecturer lecturer, int bookId) {
        try {
            bookDao.returnBook(lecturer.getId(), bookId);
            return true;
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
            return false;
        }
    }
}
