public class NotFullyPaidException extends Exception {

    public NotFullyPaidException (String message,long remaining){
        super(message);
    }

    public NotFullyPaidException(String s) {

    }

    public void getRemaining(){
        System.out.println("Remaining money to be added ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
