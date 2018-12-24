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

    public Display(){
        setBoard();
        setVisible(true);
        pack();
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   
    }

    private void setBoard(){
        button = new JButton[9];    
		
		for (int i=0; i<this.button.length; i++){
			button[i] = new JButton("");
        }
        
		label = new JLabel("Player 1s' turn");
		reset = new JButton("Play Again");
        reset.setEnabled(false);

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

    public void showResult( String player ){
        label.setText( "Player "+ player + " Won" );
        JOptionPane.showMessageDialog( this, "Congratulations Player "+ player + " WON","Game Result",JOptionPane.INFORMATION_MESSAGE);
        for(JButton jButton : this.button){
            jButton.setEnabled(false);
        }
        reset.setEnabled(true);
    }

    public void showResult(){
        label.setText( "Game Draw" );
        JOptionPane.showMessageDialog(this,"Draw","Game Result",JOptionPane.INFORMATION_MESSAGE);
        reset.setEnabled(true);
    }

    public void setLabel( String playerStr ){
        label.setText( "Player "+ playerStr+"s' turn" );
    }
    
}