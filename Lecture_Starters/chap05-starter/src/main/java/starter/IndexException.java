package starter;

public class IndexException extends RuntimeException {

    public IndexException(){
        //Calls default constructor of parent class
    }
    public IndexException(String message) {
        super(message);
    }
}
