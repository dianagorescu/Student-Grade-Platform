import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

interface Strategy {
    Student strategy_score(HashMap<Student, Grade> gradeHash);
}
class BestPartialScore implements Strategy {

    public Student strategy_score(HashMap<Student, Grade> gradeHash) {
        Double max_score = 0.00;
        Student s = new Student(null, null);
        for (Map.Entry<Student, Grade> entry : gradeHash.entrySet()) {
            Double grade = entry.getValue().getPartialScore();
            if (grade > max_score) {
                max_score = grade;
                s = entry.getKey();
            }
        }
        return s;
    }
}
class BestExamScore implements Strategy {
    public Student strategy_score(HashMap<Student, Grade> gradeHash) {
        Double max_score = 0.00;
        Student s = new Student(null, null);
        for (Map.Entry<Student, Grade> entry : gradeHash.entrySet()) {
            Double grade = entry.getValue().getExamScore();
            if (grade > max_score) {
                max_score = grade;
                s = entry.getKey();
            }
        }
        return s;

    }
}

    class BestTotalScore implements Strategy {
        public Student strategy_score(HashMap<Student, Grade> gradeHash) {
            Double max_score = 0.00;
            Student s = new Student(null, null);
            for (Map.Entry<Student, Grade> entry : gradeHash.entrySet()) {
                Double grade = entry.getValue().getTotal();
                if (grade > max_score) {
                    max_score = grade;
                    s = entry.getKey();
                }
            }
            return s;
        }
    }

