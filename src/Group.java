import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Group extends ArrayList<Student> {
    private String ID;
    private ArrayList<Student> theStudents= new ArrayList<Student>();
    private Assistant assistant;
    private Comparator<Student> comp;
    public String getID(){return ID;}
    public Assistant getAssistant(){
        return assistant;
    }
    public ArrayList<Student> getTheStudents(){
        return theStudents;
    }
    /////

    public void setID(String ID){this.ID = ID;}
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
    public void setStudents(ArrayList<Student> students) {
        this.theStudents = students;
    }


    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        this.ID = ID;
        this.assistant = assistant;
        Collections.sort(theStudents, comp);
        this.comp = comp;
    }
    public Group(String ID, Assistant assistant) {
        this.ID = ID;
        this.assistant = assistant;
    }

    @Override
    public String toString() {
        return assistant + "\n" +theStudents + "\n";
    }
}
