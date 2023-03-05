import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class StudentPage extends JFrame implements ActionListener {
    JPanel panel;
    JLabel m1, m2, m3, m4, m5;
    JButton submit, button;
    public StudentPage(String f, String l, List<Course> courses){
        String firstname = f, lastname = l;

        panel = new JPanel(new GridLayout(3, 1));
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(f+ " " + l);
        m1 = new JLabel();
        m1.setBounds(10, 150, 500, 25);
        m2 = new JLabel();
        m2.setBounds(10, 170, 500, 25);
        m3 = new JLabel();
        m3.setBounds(10, 190, 500, 25);
        m4 = new JLabel();
        m4.setBounds(10, 210, 500, 25);
        m5 = new JLabel();
        m5.setBounds(10, 230, 500, 25);
        panel.setBackground(Color.PINK);

        add(panel, BorderLayout.CENTER);

        panel.setLayout(null);
        Student stud = null;
        List<Course> coursename = new ArrayList<>();
        List<JButton> buttonscursuri = new ArrayList<>();

        for(int i=0; i<courses.size(); i++){
            ArrayList<Student> students = courses.get(i).getAllStudents();
            for(int j=0; j<students.size(); j++){
                if(students.get(j).getFirstName().equals(firstname) && students.get(j).getLastName().equals(lastname)){
                    stud = students.get(j);
                    JButton c = new JButton(courses.get(i).getName());
                    coursename.add(courses.get(i));
                    buttonscursuri.add(c);
                }
            }
        }
        Student student = stud;

        for(int i=0;i<coursename.size();i++){

                submit = new JButton(coursename.get(i).getName());
                Course c1 = coursename.get(i);
                Grade g = c1.getGrade(student);

                Assistant a1 = null;
                Hashtable<String, Group> grupuri = c1.getHash();
                for(Map.Entry<String, Group> entry: grupuri.entrySet()) {
                    ArrayList<Student> Students = grupuri.get(entry.getKey()).getTheStudents();
                    if(Students.contains(student))
                        a1 = entry.getValue().getAssistant();
                }
                submit.setBounds(130, 20+i*30, 200, 25);
                Assistant finalA = a1;
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        m1.setText("PROFESOR CURS: " + c1.getFullTeacher().toString() + "\n");
                        m2.setText("ASISTENTI: " + c1.getAssistants() + "\n");
                        m3.setText("ASISTENT LABORATOR: " + finalA + "\n");
                        m4.setText("PARINTI: " + student.getMother() + " " +student.getFather());
                        m5.setText("NOTE: " + g);


                    }
                });
                panel.add(submit);
        }


        panel.add(m1, BorderLayout.SOUTH);
        panel.add(m2, BorderLayout.SOUTH);
        panel.add(m3, BorderLayout.SOUTH);
        panel.add(m4, BorderLayout.SOUTH);
        panel.add(m5, BorderLayout.SOUTH);

        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
