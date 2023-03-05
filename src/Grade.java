public class Grade implements Comparable<Grade>, Cloneable{
    private Double partialScore, examScore, totalScore;
    private Student student;
    private String course; // Numele cursului

    //implementare metode GETTER SI SETTER
    public Double getPartialScore(){
        return partialScore;
    }
    public Double getExamScore(){
        return examScore;
    }
    public Student getStudent(){
        return student;
    }
    public String getCourse(){return course;}
    public void setPartialScore(Double newp){
        this.partialScore = newp;
    }
    public void setExamScore(Double newp){
        this.examScore = newp;
    }
    public void setStudent(Student news){
        this.student = news;
    }
    public void setCourse(String newc){
        this.course = newc;
    }
    public Double getTotal() {return partialScore + examScore;}
    public void setTotalScore(Double newt){
        this.totalScore = newt;
    }


    @Override
    public String toString() {
        return "partial: " + partialScore + " exam: " + examScore;
    }

    @Override
    public Grade clone() {
        try {
            Grade clone = (Grade) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Grade grade) {
        if(this.getTotal() > grade.getTotal())
            return 1;
        else
            if(this.getTotal() < grade.getTotal())
                return -1;
            else
                return 0;
    }
}
