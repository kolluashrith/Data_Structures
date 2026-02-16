package starter.exercise;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("all")
public class MoocRoster implements Roster {
  private final Student[] students;
  private int numStudents;

  public MoocRoster(int size) {
    students = new Student[size];
    numStudents = 0;
  }

  @Override
  public void add(Student s) {
    if (numStudents < students.length) {
      students[numStudents++] = s;
    }
  }

  @Override
  public void remove(Student s) {
    // stub - leave it as is
  }

  @Override
  public Student find(String email) {
    return null; // stub - leave it as is
  }

  @Override
  public Iterator<Student> iterator() {
    return new moocRosterIterator();
  }

  private class moocRosterIterator implements Iterator<Student> {

    private int cursor = 0;

    @Override
    public boolean hasNext() {
      return cursor < numStudents;
    }

    @Override
    public Student next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return students[cursor++];
    }
  }
}
