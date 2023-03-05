import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Testnou extends JFrame implements ActionListener {
    JPanel panel;
    JLabel fname, lname, type;
    JTextField fnametext, lnametext, typetext;
    JButton submit;
    public Testnou(){
        panel = new JPanel(new GridLayout(3, 1));
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("***PRINCIPAL PAGE***");
        panel.setBackground(Color.decode("#87ec97") );
        add(panel, BorderLayout.CENTER);


        panel.setLayout(null);


        fname = new JLabel("First name :");
        fname.setBounds(10, 20, 80, 25);
        panel.add(fname);

        fnametext = new JTextField(20);
        fnametext.setBounds(100, 20, 165, 25);
        panel.add(fnametext);


        lname = new JLabel("Last name :");
        lname.setBounds(10, 50, 80, 25);
        panel.add(lname);

        lnametext = new JTextField();
        lnametext.setBounds(100, 50, 165, 25);
        panel.add(lnametext);

        type = new JLabel("Type :");
        type.setBounds(10, 80, 80, 25);
        panel.add(type);

        typetext = new JTextField(20);
        typetext.setBounds(100, 80, 165, 25);
        panel.add(typetext);


        submit = new JButton("SEARCH");
        submit.setBounds(130, 110, 100, 25);
        submit.addActionListener(this);
        panel.add(submit);

        setVisible(true);


    }
    Catalog catalog = Catalog.getInstance(); //CREARE CATALOG
    public void read(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String nume = input.next();
            String nume_curs = nume.replaceAll("/", " ");

            String nr_credits = input.next();
            String type_curs = input.next();
            String strategyy = input.next();
            Teacher t = new Teacher(input.next(), input.next());
            HashSet<Assistant> assistants = new HashSet<>();
            ArrayList<Grade> grades = new ArrayList<>();
            Hashtable<String, Group> groups_hash = new Hashtable<String, Group>();

            Course c1 = new FullCourse.FullCourseBuilder(nume_curs)
                    .setTeacher(t)
                    .setType(type_curs)
                    .setStr(strategyy)
                    .setgcredits(Integer.parseInt(nr_credits))
                    .build();

            while (input.hasNext()) {
                ArrayList<Student> students = new ArrayList<>();
                String nume_grupa = input.next();
                if(nume_grupa.equals("*"))
                    break;
                String fname = input.next();
                String lname = input.next();

                Assistant a = new Assistant(fname, lname);
                assistants.add(a);

                while (input.hasNext()) {
                    fname = input.next();
                    if (fname.equals("#"))
                        break;
                    Student s = new Student(fname, input.next());
                    Grade g = new Grade();
                    g.setStudent(s);
                    g.setCourse(nume_curs);

                    String word = input.next();
                    if (!word.equals("-")) {
                        s.setMother(new Parent(word, input.next()));
                    } else {
                        s.setMother(new Parent(null, null));
                        String sari_peste = input.next();
                    }
                    word = input.next();
                    if (!word.equals("-")) {
                        s.setFather(new Parent(word, input.next()));
                    } else {
                        s.setFather(new Parent(null, null));
                        String sari_peste = input.next();
                    }
                    String nota = input.next();
                    if (!nota.equals("-")) {
                        Double nota_exam = Double.parseDouble(nota);
                        g.setExamScore(nota_exam);

                    }
                    nota = input.next();
                    if (!nota.equals("-")) {
                        Double nota_partial = Double.parseDouble(nota);
                        g.setPartialScore(nota_partial);
                    }
                    students.add(s);
                    grades.add(g);
                    c1.setAssistants(assistants);
                }
                Group group = new Group(nume_grupa, a);
                group.setStudents(students);
                groups_hash.put(nume_grupa, group);
            }
            c1.setGrades(grades);
            c1.setHash(groups_hash);
            catalog.addCourse(c1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentPage studentbutton = null;

        String Firstname = fnametext.getText();
        String Lastname = lnametext.getText();
        String type = typetext.getText();

        if (type.equals("Student") || type.equals("student")) {
            for (int i = 0; i < catalog.courses.size(); i++) {
                ArrayList<Student> students = catalog.courses.get(i).getAllStudents();
                for (int j = 0; j < students.size(); j++) {
                    if (students.get(j).getFirstName().equals(Firstname) && students.get(j).getLastName().equals(Lastname))
                        studentbutton = new StudentPage(Firstname, Lastname, catalog.courses);
                }
            }

        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Testnou testare = new Testnou();
        Catalog catalog = Catalog.getInstance(); //CREARE CATALOG
        testare.read("C:\\Users\\diana\\IdeaProjects\\LAB5\\TEMA-POO\\src\\cursuri.txt");
        //////TESTARE///////
        //Returneaza toti studentii primului curs
        ArrayList<Student> lista = catalog.courses.get(0).getAllStudents();
        //System.out.println(lista);
        //Arata grupele pt fiecare curs
        for(Course c : catalog.courses)
            System.out.println("///////"+c.getName()+"//////\n"+c.groups_hash);
        //Arata notele tuturor studentilor la fiecare curs
        for(Course c : catalog.courses) {
            lista = c.getAllStudents();
            System.out.println("///////"+c.getName()+"//////");
            for(int i=0;i<lista.size();i++)
                System.out.println(lista.get(i).toString()
                        + "-->partial: " + c.getGrade(lista.get(i)).getPartialScore()
                        + "-->exam: " + c.getGrade(lista.get(i)).getExamScore());
        }
        //Arata notele studentilor primului curs
        System.out.println("///////"+catalog.courses.get(0).getName()+"//////");
        HashMap<Student, Grade> AllStudentGrades = catalog.courses.get(0).gettAllStudentGrades();
        System.out.println(AllStudentGrades);
        //Adauga asistent pt al doilea curs
        Course c = catalog.courses.get(1);
        System.out.println("BEFORE: " +c.getAssistants());
        c.addAssistant("312CC", new Assistant("Mircea", "Ion"));
        System.out.println("AFTER: " +c.getAssistants());
        //////////STRATEGY//////////
        for(Course c1 : catalog.courses) {
            String strategy=c1.getStr();
            Student bestS = null;
//            if(strategy.equals("BestPartialScore"))
//                bestS = c1.getBestStudent(new BestPartialScore());
//            if(strategy.equals("BestExamScore"))
//                bestS = c1.getBestStudent(new BestExamScore());
//            if(strategy.equals("BestTotalScore"))
//                bestS = c1.getBestStudent(new BestTotalScore());
            System.out.println("///////" + c1.getName() + " =>strategy:  " + strategy+ " =>BestStudent:  " + bestS);
        }
        //Parintii studentilor primului curs
        HashMap<Student, Grade> hash = c.gettAllStudentGrades();
        for(Map.Entry<Student, Grade> entry: hash.entrySet()) {
            Student s = entry.getKey();
            System.out.println(s.toString() + "-->" + s.getMother() + "-->" + s.getFather());
        }


    }
}
