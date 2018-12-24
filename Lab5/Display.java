/*
    Lab 05 | TicTacToe Game
    E/15/131
*/
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Display extends JFrame{
    private static final long serialVersionUID = 1L;
    public JButton[] button;
    public JButton reset;
    public JLabel label;

    public Display(){       //Constructor
        setBoard();         //Set the playing Board
        setVisible(true);
        pack();
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   
    }

    //Method to set the Playing Board with buttons and label
    private void setBoard(){
        button = new JButton[9];                    //9 Buttons for game play
		for (int i=0; i<this.button.length; i++){
			button[i] = new JButton("");
        }
        
		label = new JLabel("Player 1s' turn");      //Label to Display which players turn
		reset = new JButton("Play Again");          //Button to reset the game after a result is achieved
        reset.setEnabled(false);

        //Adding the Buttons to display and grouping it togather and formating
        //Setting Button Sizes and formating the gaps between buttons
        GroupLayout border = new GroupLayout(getContentPane());
        getContentPane().setLayout(border);
        border.setAutoCreateGaps(true);
        border.setAutoCreateContainerGaps(true);

        border.setHorizontalGroup(border.createParallelGroup()
                    .addComponent(reset,GroupLayout.Alignment.CENTER, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE )
                    .addComponent(label,GroupLayout.Alignment.CENTER)
                    .addGroup(border.createSequentialGroup()
                            .addGroup(border.createParallelGroup()
                                    .addComponent(button[0], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[1], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[2], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE ))
                            .addGroup(border.createParallelGroup()
                                    .addComponent(button[3], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[4], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[5], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE ))
                            .addGroup(border.createParallelGroup()
                                    .addComponent(button[6], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[7], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE )
                                    .addComponent(button[8], GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE ))
                    )        
        );

        border.setVerticalGroup(border.createSequentialGroup()
                    .addComponent(reset, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE )
                    .addGroup(border.createSequentialGroup()
                            .addGroup(border.createParallelGroup()
                                   .addComponent(button[0], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[3], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[6], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE ))
                            .addGroup(border.createParallelGroup()
                                   .addComponent(button[1], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[4], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[7], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE ))
                            .addGroup(border.createParallelGroup()
                                   .addComponent(button[2], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[5], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE )
                                   .addComponent(button[8], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE ))
                    )
        );
        
    }

    //Method to display the result when a player is won
    public void showResult( String player ){
        label.setText( "Player "+ player + " Won" );
        JOptionPane.showMessageDialog( this, "Congratulations Player "+ player + " wins!","Game Result",JOptionPane.INFORMATION_MESSAGE);
        for(JButton jButton : this.button){
            jButton.setEnabled(false);
        }
        reset.setEnabled(true);
    }

    //Method to display the result when game is drawn
    public void showResult(){
        label.setText( "Game Draw" );
        JOptionPane.showMessageDialog(this,"The game is draw","Game Result",JOptionPane.INFORMATION_MESSAGE);
        reset.setEnabled(true);
    }

    public void setLabel( String playerStr ){
        label.setText( "Player "+ playerStr+"s' turn" );
    }
    
}