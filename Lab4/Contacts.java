public class Contacts{
    private String firstName;
    private String lastName;
    private String contactNo;

    public Contacts( String firstN , String lastN, String number ) {        //Constructor
        firstName = firstN;
        lastName = lastN;
        contactNo = number;
    }

    public void printContact(){     //Method to print all Contact details
        System.out.println( firstName + " " + lastName + " : " + contactNo );
    }
}