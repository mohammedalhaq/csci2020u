package sample;

//initializes StudentRecord class
public class StudentRecord {
    private String id;
    private float midterm;
    private float assignment;
    private float finalExam;
    private double finalMark;
    private String letterG;


    StudentRecord(String id, float assignment, float midterm, float finalExam){
        this.id = id;
        this.midterm = midterm;
        this.assignment = assignment;
        this.finalExam = finalExam;
        this.finalMark = (midterm*30 + assignment*20 + finalExam*50)/100; //calculates final mark
        this.letterG = gradeLetter();
    }

    private String gradeLetter(){
        if (finalMark>79){
            return "A";
        } else if (finalMark>69){
            return "B";
        } else if (finalMark>59){
            return "C";
        }else if (finalMark>49){
            return "D";
        }else{
            return "F";
        }
    }

    //initializes getters for the SetCellValueFactory function (function name must match variable name)
    public String getId(){
        return this.id;
    }

    public float getMidterm(){
        return this.midterm;
    }

    public float getAssignment(){
        return this.assignment;
    }

    public float getFinalExam(){
        return this.finalExam;
    }

    public double getFinalMark(){
        return this.finalMark;
    }

    public String getLetterG(){
        return this.letterG;
    }

}
