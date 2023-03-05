import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartialCourse extends Course {
        public PartialCourse(PartialCourseBuilder builder) {
            super(builder);
        }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        HashMap<Student, Grade> gradeHash = gettAllStudentGrades();
        ArrayList<Student> graduatedstudents = new ArrayList<Student>();
        for(Map.Entry entry: gradeHash.entrySet()) {
            Double grade = gradeHash.get(entry.getValue()).getPartialScore();
            Student s = gradeHash.get(entry.getValue()).getStudent();
            if( grade >= 5)
                graduatedstudents.add(s);
        }
        return graduatedstudents;
    }
    public static class PartialCourseBuilder extends Course.CourseBuilder {
        public PartialCourseBuilder(String name) {
            super(name);
        }

        @Override
        public Course build() {
            return new PartialCourse(this);
        }
    }
}
