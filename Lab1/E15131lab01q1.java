/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 10/10/2018
    Lab 01 | Task 01
*/
import java.util.Scanner;

public class E15131lab01q1 {
    public static void main(String [] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = keyboard.nextInt();            //Getting Input from keyboard
        
        if( (number%3 == 0) && (number%5 == 0) ){       //Checking if number is divisible by 15( i.e divisible by 5 and 3) then number is Special
            if( (number%2 == 0) && (number%9 != 0) ){   //Checking number is wierd.(since its divible by 15 only checkin divible by 2 and not divible by 18( i.e not divible by 9))
                if( number > 999 ){     //Checking number is big
                    System.out.println(number + " is special, big, weird, scary.");
                }
                else{       //Number is not Big
                    System.out.println(number + " is special, weird, scary, but not big.");     //If number is weird then it implies number is special
                }
            }else{          //Number is not weird
                if( number > 999 ){     //Number is big
                    System.out.println(number + " is special, big, scary, but not weird.");
                }
                else{                   //Number is not big
                    System.out.println(number + " is special, but not scary.");     //If number is not scary it implies number is not big and not weird
                }
            }
        }
        else{       //Number is not special and it implies number is not weird
            if( number > 999 ){
                System.out.println(number + " is big, scary, but not weird.");  //Number is big and scary but not special.
            }
            else{
                System.out.println(number + " is not scary.");     //Number is not Scary( implies number is not big and not weird) and not special
            }
        }
    }
}