/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 10/12/2018
    Lab 04 | Data exchange format
*/
import java.io.*;
import java.util.*;

public class Main{
    private static Map<String, Contacts> contactDetails = new HashMap< String, Contacts >();    //HashMap to store all Contact Details
    static int numberOfContacts=0;      //Number of contacts found after search
    static final int FirstNameID = 0;          //A unique ID for search Firstname
    static final int LastNameID = 1;           //A unique ID for search Lastname

    public static void main(String [] args){
        
        addContacts();          //Read CSV file and store in HashMap
        
        try{
            System.out.println("Enter the name (F:firstname or L:lastname)");
            Scanner scanner = new Scanner(System.in);       //Get input from user( Firstname or Lastname )   
            String[] input = scanner.next().split(":");     //Split user input by colan(:)
            String name = input[1];     //Store name to be search
        
            if( input[0].equalsIgnoreCase("F") ){
                searchContact( name, FirstNameID );     //Search by Firstname
            }
            else if ( input[0].equalsIgnoreCase("L") ){
                searchContact( name, LastNameID );      //Search by Lasttname
            }
            else{
                System.out.println("Wrong Input Prefix");
            }
        }catch( ArrayIndexOutOfBoundsException e ){
            System.out.println("Not enough arguments");
        }
    }

    //Method to read and store conatcts details from CSV file
    public static void addContacts() {
        String [] splitContact;
        String firstName;
        String lastName;
        String fullName;
        String contactNum;
        BufferedReader csvRead = null;
        String contactRead="";

        try{
            csvRead = new BufferedReader( new FileReader("contacts.csv") );
            contactRead = csvRead.readLine();

            while( (contactRead = csvRead.readLine()) != null ){
                splitContact = contactRead.split(",");      //Seperate string by coma(,)
                firstName = splitContact[0];
                lastName = splitContact[1];
                contactNum = splitContact[2];
                fullName = firstName + " " + lastName;      //Combine firstname and lastname to get fullname, Fullname is used as key for hashmap

                Contacts contact = new Contacts( firstName, lastName, contactNum ); //Create a Contact Object
                
                contactDetails.put( fullName, contact);        //Create a key and object

            }

        } catch (FileNotFoundException e) {     //Exception Handling
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( csvRead != null ) {
                try {
                    csvRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Method to search First and Last names
    public static void searchContact( String searchName, int ID ) {
        for( Map.Entry< String, Contacts > entry : contactDetails.entrySet() ){
            String key = entry.getKey();   
            String []name=key.split(" ");       //Split the key by space
            
            if( searchName.equalsIgnoreCase( name[ID] ) ){  //Check for search name after spliting( FirstNameID = 0 and LastNamesID = 1  )
                Contacts contact = entry.getValue();
                contact.printContact();                 //Print contact details
                numberOfContacts++;
            }
        }
        System.out.println( numberOfContacts + " Contacts found" );  //Print Number of contacts found
    }

}