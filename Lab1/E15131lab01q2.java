/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 10/10/2018
    Lab 01 | Task 02
*/
import java.util.Scanner;

public class E15131lab01q2 {
    public static void main(String [] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter the color: ");
        int red = keyboard.nextInt();           //Getting Input Red from keyboard
        int green = keyboard.nextInt();         //Getting Input Green from keyboard
        int blue = keyboard.nextInt();          //Getting Input Blue from keyboard
        
        if( ((red > 111) && (red < 144)) && ((green > 111) && (green < 144)) && ((blue > 111) && (blue < 144))  ){  //Checking For Gray Colors
            if(red < 128){      //Calculating alternate complement of Red
                red += 128;     //Adding 128 if components value is less than 128
            }
            else{
                red -= 128;     //Subtracting 128 if components value is greater than 128
            }
            if(green < 128){    //Calculating alternate complement of Green
                green += 128;   //Adding 128 if components value is less than 128
            }
            else{
                green -= 128;   //Subtracting 128 if components value is greater than 128
            }
            if(blue < 128){     //Calculating alternate complement of Blue
                blue += 128;    //Adding 128 if components value is less than 128
            }
            else{
                blue -= 128;    //Subtracting 128 if components value is greater than 128
            }
            System.out.print("The complement: "+red+" "+green+" "+blue);    //Printing the complement valus of RGB
        }
        else if( ((red >= 0) && (red <= 255)) && ((green >= 0) && (green <= 255)) && ((blue >= 0 ) && (blue <= 255)) ){
            red = 255 - red;        //Calculating complement of Red
            green = 255 - green;    //Calculating complement of Green
            blue = 255 - blue;      //Calculating complement of Blue
            System.out.print("The complement: "+red+" "+green+" "+blue);    //Printing the complement valus of RGB
        }
    }
}