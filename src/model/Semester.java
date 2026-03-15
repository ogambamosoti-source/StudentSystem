package model;


public class Semester{
    private int academicYear;//e.g 1-6
    private int semester;//e.g 1or 2
    private String startDate;//"2026-06-04"
    private String endDate;//"2026-05-03"

    public Semester(int academicYear,int semester,String startDate,String endDate){
        this.academicYear = academicYear;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getAcademicYear(){
        return academicYear;
    }
    public void setacademicYear(int academicYear){
        if(academicYear < 1 && academicYear > 6){
            throw new IllegalArgumentException("Academic year must be between 1 and 6");
        }
        this.academicYear = academicYear;
    }
    public int getSemester(){
        return semester;
    }
    public void setSemester(int semester){
        if(semester < 1 && semester > 2){
            throw new IllegalArgumentException("semester must be between 1 and 2");
        }
        this.semester = semester;
    }
    public String getStartDate(){
        return startDate;
    }
    public void setstartDate(String startDate){
        this.startDate = startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public void  setEndDate(String endDate){
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        return"Semester{"+
        "academicYear='"+ academicYear + '\''+
        ",semester='"+ semester +'\''+
        ",startDate='"+ startDate +'\'' +
        ",endDate='"+ endDate + '\''+
        '}';
    }
}

