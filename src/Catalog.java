import java.util.ArrayList;
import java.util.List;

public class Catalog implements Subject{

    private static Catalog catalog = null;
    public String name = "";
    public List<Course> courses;
    private List<Observer> observers;
    Notification notification;
    private Catalog(String name) {

        this.name = name;
        courses = new ArrayList<Course>();
    }
    public static Catalog getInstance() {
        if (catalog == null)
            catalog = new Catalog("Catalog1");
        return catalog;
    }
    // Adauga un curs în catalog
    public void addCourse(Course course) {
        courses.add(course);
    }
    // Sterge un curs din catalog
    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public void addObserver(Observer observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for(Observer obs : observers) {
            obs.update(notification);
            System.out.println("Nota este " + grade + ".");
        }
    }
}