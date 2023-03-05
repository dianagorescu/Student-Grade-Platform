import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullCourse extends Course {
    public FullCourse(FullCourseBuilder builder) {
        super(builder);
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        HashMap<Student, Grade> gradeHash = gettAllStudentGrades();
        ArrayList<Student> graduatedstudents = new ArrayList<Student>();
        for(Map.Entry entry: gradeHash.entrySet()) {
            Double partial = gradeHash.get(entry.getValue()).getPartialScore();
            Double exam = gradeHash.get(entry.getValue()).getExamScore();
            Student s = gradeHash.get(entry.getValue()).getStudent();
            if( partial >= 3 && exam >= 2)
                graduatedstudents.add(s);
        }
        return graduatedstudents;
    }
    public static class FullCourseBuilder extends Course.CourseBuilder {
        public FullCourseBuilder(String name) {
            super(name);
        }

        @Override
        public Course build() {
            return new FullCourse(this);
        }
    }
}
