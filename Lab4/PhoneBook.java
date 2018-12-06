import java.io.*;
import java.util.*;

public class PhoneBook{
    public static void main(String [] args){

        String [] splitContact;
        String firstName;
        String lastName;
        String contactNum;
        Contacts [] contacts = new Contacts[1000];
        int count=0;
        int numberOfContacts=0;

        try{
            FileReader fileRead = new FileReader("contacts.csv");
            BufferedReader csvRead = new BufferedReader(fileRead);

            String header = csvRead.readLine();

            for( String contactRead = csvRead.readLine(); contactRead != null; contactRead = csvRead.readLine()){
                
                splitContact = contactRead.split(",");
                firstName = splitContact[0];
                lastName = splitContact[1];
                contactNum = splitContact[2];

                contacts[count] = new Contacts( firstName, lastName, contactNum );
                count++;
            }


            Scanner scanner = new Scanner(System.in);          
            String[] input = scanner.next().split(":");
        

            if( input[0].equalsIgnoreCase("F") ){
                for(int i=0; i<contacts.length; i++ ){
                    if( input[1].equalsIgnoreCase( contacts[i].getFirstName() ) ){
                        System.out.println( contacts[i].getFirstName() + " " + contacts[i].getLastName() + " " + contacts[i].getNumber() );
                        numberOfContacts++;
                    }
                }
            }
            else if ( input[0].equalsIgnoreCase("L") ){
                for(int i=0; i<contacts.length; i++ ){
                    if( input[1].equalsIgnoreCase( contacts[i].getLastName() ) ){
                        System.out.println( contacts[i].getFirstName() + " " + contacts[i].getLastName() + " " + contacts[i].getNumber() );
                        numberOfContacts++;
                    }
                }
            }
            else{
                System.out.println("Wrong input prefix");
            }

            csvRead.close();
            scanner.close();
        }
        catch( ArrayIndexOutOfBoundsException e ){
            System.out.println("Not enough arguments");
        }
        catch( NullPointerException e ){
            System.out.println( numberOfContacts + " Contacts found" );
        }
        catch( IOException e ){
            System.out.println("Wrong input");
        }
    }
}