import java.util.Hashtable;

public class ScoreVisitor implements Visitor{
    Hashtable<Teacher, Tuple> examScores = new Hashtable<Teacher, Tuple>();
    Hashtable<Assistant, Tuple> partialScores = new Hashtable<Assistant, Tuple>();
    Catalog catalog = Catalog.getInstance();

    @Override
    public void visit(Assistant assistant) {
        Tuple t = partialScores.get(assistant);
        Grade g = new Grade();
        g.setStudent((Student) t.getStudent());
        g.setCourse((String) t.getCoursename());
        g.setPartialScore((Double) t.getCoursegrade());
        catalog.notifyObservers(g);
    }

    @Override
    public void visit(Teacher teacher) {
        Tuple t = examScores.get(teacher);
        Grade g = new Grade();
        g.setStudent((Student) t.getStudent());
        g.setCourse((String) t.getCoursename());
        g.setExamScore((Double) t.getCoursegrade());
        catalog.notifyObservers(g);

    }
    private class Tuple<Student, String, Double>{
        private final Student student;
        private final String coursename;
        private final Double coursegrade;

        private Tuple(final Student student, final String coursename, final Double coursegrade) {
            this.student = student;
            this.coursename = coursename;
            this.coursegrade = coursegrade;
        }
//        private <Student, String, Double> Tuple of (final Student val1, final String val2, final Double val3){
//            return new Tuple(val1,val2,val3);
//        }
        private Student getStudent(){return student;}
        private String getCoursename(){return coursename;}
        private Double getCoursegrade(){return coursegrade;}
    }
}
