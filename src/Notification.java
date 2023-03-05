
public class Notification{
    Grade grade;
    public Notification(Grade grade){
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Notificare Catalog Virtual! Copilul tau a primit o nota "+grade+" !";
    }
}