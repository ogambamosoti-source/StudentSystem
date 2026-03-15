package model;
public class Program {
    private String programName;
    private int programID;
    private LevelEnum level; // e.g., DEGREE, DIPLOMA, etc.
    private String durationYears;
    private int departmentId;

    public Program(String programName, int programID, LevelEnum level, String durationYears,int departmentId) {
        this.programName = programName;
        this.programID = programID;
        this.level = level;
        this.durationYears = durationYears;
        this.departmentId = departmentId;
    }

    public Program(String string) {
        //TODO Auto-generated constructor stub
    }

    public String getProgramName() {
        return programName;
    }

    public int getProgramID() {
        return programID;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public String getDurationYears() {
        return durationYears;
    }
    public int getDepartmentId(){
        return departmentId;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programName='" + programName + '\'' +
                ", programID=" + programID +
                ", level=" + level +
                ", durationYears='" + durationYears + '\'' +
                '}';
    }
}
