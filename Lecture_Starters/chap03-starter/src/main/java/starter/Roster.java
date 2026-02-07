package starter;

@SuppressWarnings("all")
public abstract class Roster { //Class cannot be directly instantiated because of abstract
  private Student[] students;
  private int numStudents;

  public Roster(int size) {
    students = new Student[size];
    numStudents = 0;
  }

  public abstract void add(Student s);

  public abstract void remove(Student s);

  public abstract Student find(String email);
}
