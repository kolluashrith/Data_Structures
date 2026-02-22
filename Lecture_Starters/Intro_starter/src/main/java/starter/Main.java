package starter;

public class Main {
  /**
   * Execution starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {

    System.out.println("Hello Data Structures!");
    Student bob = new Student("bob", "bob@jh.edu");
    Student timmy = new Student("timmy", "timmy@jh.edu");

    System.out.println("Bob's email: " + bob.getEmail());
  }
}
