/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 24/12/2018
    Lab 05 | TicTacToe Game
*/
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class TicTacToe implements ActionListener{
    private static Display display;
    private static TicTacToe TTT;
    private static TTTEngine tttEngine;
    public final static String PLAYER1 = "1";
    public final static String PLAYER2 = "2";
    public static String player;

    public static void main(String[] args) {
        TTT = new TicTacToe();
        display = new Display();            //Create a new the frame
        tttEngine = new TTTEngine();        //Initiate TicTacToe game engine i.e start the game
        setListner();                       //Assign buttons to ActionListener
    }

    private static void setListner(){
        player = PLAYER1;
        for(JButton jButton : display.button){      //Assigning 9 buttons to ActionListener
            jButton.addActionListener( TTT );
        }
        display.reset.addActionListener( TTT );     //Assigning Reset buttons to ActionListener
    }

    //Method to record when a button is pressed & check for winning possibilities
    //Returns True if a player is won, false otherwise
    private boolean recordEvent( Integer integer, String playerStr ){
        tttEngine.setGrid( integer, playerStr );        //Calling methode to recording

        if( tttEngine.counter < 5 ){
            return false;
        }
        else{           //After 5 instances check for winning possibilities
            if( tttEngine.checkResult() ){
                display.showResult( playerStr );
                return true;
            }
            if( tttEngine.counter == 9 ){
                display.showResult();
            }
            return false;
        }

    }

    private void setPlayer(String playerStr ){      //Method to determine next player and update the label accordingly
        if( playerStr.equals("1") ){
            player = PLAYER2;
            display.setLabel( player );
        }
        else if( playerStr.equals("2") ){
            player = PLAYER1;
            display.setLabel( player );
        }

    }

    //Checks for Button is clicked
    public void actionPerformed(ActionEvent event){
        if( Arrays.asList( display.button ).contains( event.getSource() ) ){
            //If a button is clicked update the Button text to current player and Disable the button
            int buttonNum = Arrays.asList( display.button ).indexOf( (JButton)event.getSource() );
            ( (JButton)event.getSource() ).setText( player );
            display.pack();
            ( (JButton)event.getSource() ).setEnabled(false);
            
            //Check for a result, if result is not achieved change player
            if( !recordEvent( buttonNum,player ) ){
                setPlayer( player );
            }
            
        }else if( event.getSource().equals(display.reset) ){    //If PlayAgain Button is clicket reset the the game 
            display.dispose();
            display = new Display();
            tttEngine = new TTTEngine();
            setListner();
        }
    }

}