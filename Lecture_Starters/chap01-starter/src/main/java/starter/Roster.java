package starter;

@SuppressWarnings("all")
public class Roster {

    private Student[] students;
    private int numStudents;

    public Roster(int maxStudents){
        students = new Students[maxStudents];
        numStudents = 0;
    }

    public void add(Student s){
        //stub, to be implemented later
    }
    public void remove (Student s){
        //stub, to be implemented later
    }

    public Student find(String email){
        //FOR BINARY SEARCH:
        return find(email, 0, numStudents-1);

        //FOR LINEAR SEARCH:
//        for (int i = 0; i < numStudents; i++){
//            if (students[i].getEmail().equals(email)){
//                return students[i];
//            }
//        }
//        return null;
    }

    //Helper method to help implement binary search
    //Assumes list is in order and student emails are unique
    private Student find(String email, int first, int last) {
        if (last < first) return null;

        int middle = (last+first)/2;
        int comparisonResult = email.compareTo(students[middle].getEmail());
        if (comparisonResult == 0) {
            return students[middle];
        }
        else if (comparisonResult > 0) {
            return find(email, mid + 1, last);
        }
        else {
            return find(email, first, mid - 1);
        }
    }
}
