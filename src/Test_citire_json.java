import java.io.FileReader;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Test_citire_json
{
    public static void main(String[] args) throws Exception {
        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\diana\\IdeaProjects\\LAB5\\TEMA-POO\\src\\Test_citire.json"));
        JSONObject jo = (JSONObject) obj;

        Catalog catalog = Catalog.getInstance();//CREARE CATALOG
        JSONArray courses = (JSONArray) jo.get("courses");
        for (int i = 0; i < courses.size(); i++) {

            JSONObject jsonObjectRow = (JSONObject) courses.get(i);
            String type_curs = (String) jsonObjectRow.get("type");
            String nume_curs = (String) jsonObjectRow.get("name");
            String strategie_curs = (String) jsonObjectRow.get("strategie");

            JSONObject teacher = (JSONObject) jsonObjectRow.get("teacher");
            String fname = (String) teacher.get("firstName");
            String lname = (String) teacher.get("lastName");
            Teacher t = new Teacher(fname, lname);

            Course c1 = new FullCourse.FullCourseBuilder(nume_curs)
                    .setTeacher(t)
                    .setType(type_curs)
                    .setStr(strategie_curs)
                    .build();

            JSONArray assistants = (JSONArray) jsonObjectRow.get("assistants");
            HashSet<Assistant> Assistants = new HashSet<>();
            for (int j = 0; j < assistants.size(); j++) {

                JSONObject as = (JSONObject) assistants.get(j);
                fname = (String) as.get("firstName");
                lname = (String) as.get("lastName");
                Assistant a = new Assistant(fname, lname);
                Assistants.add(a);
            }
            c1.setAssistants(Assistants);

            JSONArray groups = (JSONArray) jsonObjectRow.get("groups");
            Hashtable<String, Group> groups_hash = new Hashtable<>();

            for (int j = 0; j < groups.size(); j++) {
                JSONObject gr = (JSONObject) groups.get(j);
                String id = (String) gr.get("ID");
                JSONObject asis = (JSONObject) gr.get("assistant");
                fname = (String) asis.get("firstName");
                lname = (String) asis.get("lastName");

                Group g = new Group(id, new Assistant(fname, lname));

                JSONArray students = (JSONArray) gr.get("students");
                ArrayList<Student> studentArrayList = new ArrayList<>();
                for (int l = 0; l < students.size(); l++) {
                    JSONObject stud = (JSONObject) students.get(l);
                    fname = (String) stud.get("firstName");
                    lname = (String) stud.get("lastName");
                    Student s = new Student(fname, lname);

                    JSONObject mot = (JSONObject) stud.get("mother");
                    if(mot != null){
                    fname = (String) mot.get("firstName");
                    lname = (String) mot.get("lastName");
                    s.setMother(new Parent(fname, lname));}
                    else{
                        s.setMother(new Parent(null, null));}

                    JSONObject fat = (JSONObject) stud.get("father");
                    if(fat != null){
                    fname = (String) fat.get("firstName");
                    lname = (String) fat.get("lastName");
                    s.setFather(new Parent(fname, lname));}
                    else{
                        s.setFather(new Parent(null, null));}
                    studentArrayList.add(s);
                }
                g.setStudents(studentArrayList);
                groups_hash.put(id, g);
            }
            c1.setHash(groups_hash);

            catalog.addCourse(c1);
        }
        /////////////////EXAM GRADES
        ArrayList<Grade> gradeArrayList = new ArrayList<>();
        JSONArray examgrades = (JSONArray) jo.get("examScores");
        System.out.println("EXAM SIZE: " + examgrades.size());

        for (int i = 0; i < examgrades.size(); i++) {
            JSONObject jsonObjectRow = (JSONObject) examgrades.get(i);
            JSONObject teacher = (JSONObject) jsonObjectRow.get("teacher");
            String fname = (String) teacher.get("firstName");
            String lname = (String) teacher.get("lastName");
            Teacher t = new Teacher(fname, lname);

            JSONObject student = (JSONObject) jsonObjectRow.get("student");
            fname = (String) student.get("firstName");
            lname = (String) student.get("lastName");
            Student s = new Student(fname, lname);

            String nume_curs = (String) jsonObjectRow.get("course");
            double grade = ((Number) jsonObjectRow.get("grade")).doubleValue(); // mixed long and double into same variable

            Grade g = new Grade();
            g.setExamScore(grade);
            g.setCourse(nume_curs);
            g.setStudent(s);

            gradeArrayList.add(g);
        }
        /////////////////PARTIAL GRADES
        ArrayList<Grade> examgradeArrayList = gradeArrayList;
        JSONArray partialgrades = (JSONArray) jo.get("partialScores");
        System.out.println("PARTIAL SIZE: " + partialgrades.size());
        for (int i = 0; i < partialgrades.size(); i++) {
            JSONObject jsonObjectRow = (JSONObject) partialgrades.get(i);
            JSONObject assistant = (JSONObject) jsonObjectRow.get("assistant");
            String fname = (String) assistant.get("firstName");
            String lname = (String) assistant.get("lastName");
            Assistant a = new Assistant(fname, lname);

            JSONObject student = (JSONObject) jsonObjectRow.get("student");
            fname = (String) student.get("firstName");
            lname = (String) student.get("lastName");
            Student s = new Student(fname, lname);

            String nume_curs = (String) jsonObjectRow.get("course");
            double grade = ((Number) jsonObjectRow.get("grade")).doubleValue(); // mixed long and double into same variable

            for(int iter=0;iter<examgradeArrayList.size();iter++)
            {
                if(nume_curs.equals(examgradeArrayList.get(iter).getCourse()) &&
                        (s.toString()).equals(examgradeArrayList.get(iter).getStudent().toString()) ){
                    gradeArrayList.get(iter).setPartialScore(grade);
                    break;
                }
            }
        }
        for(Course course : catalog.courses)
        {
            ArrayList<Grade> gradesforcourse = new ArrayList<>();
            for(Grade git: gradeArrayList) {
                System.out.println(git.getCourse());
                if ((git.getCourse()).equals(course.toString())) {
                    gradesforcourse.add(git);
                }
            }
            System.out.println(gradesforcourse);
            course.setGrades(gradesforcourse);
        }

    }
}
