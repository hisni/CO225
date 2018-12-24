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
        display = new Display();
        TTT = new TicTacToe();
        tttEngine = new TTTEngine();
        setListner();
    }

    private static void setListner(){
        player = PLAYER1;
        for(JButton jButton : display.button){
            jButton.addActionListener( TTT );
        }
        display.reset.addActionListener( TTT );
    }

    private boolean recordEvent( Integer integer, String playerStr ){
        tttEngine.setGrid( integer, playerStr );

        if( tttEngine.counter < 5 ){
            return false;
        }
        else{
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

    private void setPlayer(String playerStr ){

        if( playerStr.equals("1") ){
            player = PLAYER2;
            display.setLabel( player );
        }
        else if( playerStr.equals("2") ){
            player = PLAYER1;
            display.setLabel( player );
        }

    }

    public void actionPerformed(ActionEvent event){
        if( Arrays.asList( display.button ).contains( event.getSource() ) ){

            int buttonNum = Arrays.asList( display.button ).indexOf( (JButton)event.getSource() );
            ( (JButton)event.getSource() ).setText( player );
            display.pack();
            ( (JButton)event.getSource() ).setEnabled(false);
            
            if( !recordEvent( buttonNum,player ) ){
                setPlayer( player );
            }
            
        }else if( event.getSource().equals(display.reset) ){
            display.dispose();
            display = new Display();
            tttEngine = new TTTEngine();
            setListner();
        }
    }

}