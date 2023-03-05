import java.util.*;

public abstract class Course {
    String name, type, str;
    Teacher FullTeacher;
    HashSet<Assistant> assistants = new HashSet<Assistant>();//colectie fara duplicate
    ArrayList<Grade> grades = new ArrayList<Grade>();
    Hashtable<String, Group> groups_hash = new Hashtable<String, Group>();
    int credits;
    public Course( String str, String type, String name, Teacher FullTeacher, HashSet<Assistant> assistants, ArrayList<Grade> grades, Hashtable<String, Group> groups_hash, int credits) {
        this.str = str;
        this.type = type;
        this.name = name;
        this.FullTeacher = FullTeacher;
        this.assistants = assistants;
        this.grades = grades;
        this.groups_hash = groups_hash;
        this.credits = credits;
    }

    public Course(CourseBuilder builder) {
        this.str = builder.str;
        this.type = builder.type;
        this.name = builder.name;
        this.FullTeacher = builder.FullTeacher;
        this.assistants = builder.assistants;
        this.grades = builder.grades;
        this.groups_hash = builder.groups_hash;
        this.credits = builder.credits;
    }
    public String getStr(){
        return str;
    }
    public void setStr(String str){this.str = str;}
    public String getType(){
        return type;
    }
    public void setType(String type){this.type = type;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Teacher getFullTeacher(){
        return FullTeacher;
    }
    public void setFullTeacher(Teacher FullTeacher){
        this.FullTeacher = FullTeacher;
    }
    public HashSet<Assistant> getAssistants(){
        return assistants;
    }
    public void setAssistants(HashSet<Assistant> assistants){
        this.assistants = assistants;
    }
    public ArrayList<Grade> getGrades(){
        return grades;
    }
    public void setGrades(ArrayList<Grade> grades){this.grades = grades;}
    public Hashtable<String, Group> getHash(){
        return groups_hash;
    }
    public void setHash(Hashtable<String, Group> groups_hash){this.groups_hash = groups_hash;}
    public int getCredits(){
        return credits;
    }
    public void setCredits(int credits){
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Nume curs: " + name + "\n" +
                "Type curs: " + type + "\n" +
                "Startegie curs: " + str + "\n" +
                "Profesor curs: " + FullTeacher.toString() + "\n" +
                "Asistenti: " + assistants + "\n" +
                "Note: " + grades.toString() + "\n" +
                "Grupuri la curs: " + groups_hash.toString() + "\n";
    }

    // Seteaza asistentul în grupa cu ID-ul indicat
    // Daca nu exista deja, adaauga asistentul si în multimea asistentilor
    public void addAssistant(String ID, Assistant assistant){
        Group group = new Group(ID, assistant);
        group.setAssistant(assistant);
        if(assistants.contains(assistant) == false)
            assistants.add(assistant);
    }
    // Adauga studentul în grupa cu ID-ul indicat
    public void addStudent(String ID, Student student)
    {
        groups_hash.get(ID).add(student);
    }
    // Adauga grupa
    public void addGroup(Group group){
        groups_hash.put(group.getID(), group);
    }
    // Instantiaza o grupa si o adauga
    public void addGroup(String ID, Assistant assistant){
        Group group = new Group(ID, assistant);
        groups_hash.put(group.getID(), group);
    }
    // Instantiaza o grupa si o adauga
    public void addGroup(String ID, Assistant assist, Comparator<Student> comp){
        Group group = new Group(ID, assist, comp);
        groups_hash.put(group.getID(), group);
    }
    // Returneaza nota unui student sau null
    public Grade getGrade(Student student){
        Grade g = new Grade();
        g.setExamScore(0.00);
        g.setPartialScore(0.00);
        for(int i=0;i<this.grades.size();i++)
            if (this.grades.get(i).getStudent().equals(student))
                return this.grades.get(i);
        return g;
    }
    // Adauga o nota
    public void addGrade(Grade grade){
        grades.add(grade);
    }

    // Returneaza o lista cu toti studentii
    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> AllStudents = new ArrayList<Student>();
        for(Map.Entry entry: groups_hash.entrySet()) {
            ArrayList<Student> Students = new ArrayList<Student>();
            Students = groups_hash.get(entry.getKey()).getTheStudents();
            for(int i=0;i<Students.size();i++)
                AllStudents.add(Students.get(i));

        }
        return AllStudents;
    }
    // Returneaza un dictionar cu situatia studentilor
    public HashMap<Student, Grade> gettAllStudentGrades(){
        HashMap<Student, Grade> AllStudentGrades = new HashMap<Student, Grade>();
        ArrayList<Student> ALLSTUDENTS = getAllStudents();

        for(int i = 0; i< ALLSTUDENTS.size(); i++)
            AllStudentGrades.put(ALLSTUDENTS.get(i), getGrade(ALLSTUDENTS.get(i)));
        return AllStudentGrades;
    }
    // Metoda ce o sa fie implementata pentru a determina studentii promovati
    public abstract ArrayList<Student> getGraduatedStudents();
    public abstract static class CourseBuilder{
        private String name, type, str;
        private Teacher FullTeacher;
        private HashSet<Assistant> assistants = new HashSet<Assistant>();//colectie fara duplicate
        private ArrayList<Grade> grades = new ArrayList<Grade>();
        private Hashtable<String, Group> groups_hash = new Hashtable<String, Group>();
        private int credits;
        public CourseBuilder(String name) {
            this.name = name;
        }
        public CourseBuilder setType(String type) {
            this.type = type;
            return this;
        }
        public CourseBuilder setTeacher(Teacher FullTeacher) {
            this.FullTeacher = FullTeacher;
            return this;
        }
        public CourseBuilder setassistants(HashSet<Assistant> assistants) {
            this.assistants = assistants;
            return this;
        }
        public CourseBuilder setgrades(ArrayList<Grade> grades) {
            this.grades = grades;
            return this;
        }
        public CourseBuilder setgroups_hash(Hashtable<String, Group> groups_dict) {
            this.groups_hash = groups_hash;
            return this;
        }
        public CourseBuilder setgcredits(int credits) {
            this.credits = credits;
            return this;
        }
        public CourseBuilder setStr(String str) {
            this.str =str;
            return this;
        }
        public abstract Course build();
    }
//    private Strategy strategy;
//
//    public Course(Strategy strategy){
//        this.strategy = strategy;}

    //returneaza cel mai bun elev in functie de strategia selectata
    public Student getBestStudent(Strategy strategy){
        HashMap<Student, Grade> gradeHash = gettAllStudentGrades();
        return strategy.strategy_score(gradeHash);
    }

}
