/*
    Group Project | Auction Server
    E/15/131 | E/15/348
    Group No : 10
*/
import java.awt.*;
import javax.swing.Timer;
import java.text.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;

public class Display extends JPanel implements ActionListener {

    MainServer server;
    Timer timer ;
    JLabel[] labels;
    Label [] label;
    JButton[] showButton;
    Button[] button;
    JTextArea textArea;
    JPanel display;
    JPanel bidHistory;

    ArrayList<String>[] BidLog = new ArrayList[8];
    
    class Label{
	    JLabel jlabel;
        
        Label( JLabel label, JPanel panel, String name ){
            
            jlabel = label = new JLabel(name);
            label.setFont( new Font("Serif", Font.PLAIN, 14) );
            label.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
            label.setHorizontalAlignment( SwingConstants.CENTER );
            panel.add( label );
        }
    }

    class Button{
        JButton jbutton;
        
        Button(JButton button,JPanel panel, String name){
            jbutton = button = new JButton( name );
            button.addActionListener(Display.this);
            button.setFont(new Font("Serif", Font.PLAIN, 14));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(button);
        }
    }

    public Display(MainServer server){
        super(new BorderLayout());

        for (int i=0;i<8 ;i++ ) {
            BidLog[i] = new ArrayList<>();
        }

        this.server = server;
        labels = new JLabel[28];
        label = new Label[24];
        showButton = new JButton[8];
        button = new Button[8];
        display = new JPanel();
        bidHistory = new JPanel();
        bidHistory.setLayout(new BorderLayout());

        textArea = new JTextArea(10, 20);   //create text area to display all bids
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bidHistory.add(scrollPane);
    
        display.setLayout(new GridLayout(9, 4));

        setHeader(0,"Symbol");
        setHeader(1,"Security Name");
        setHeader(2,"Current Price");
        setHeader(3," Show Bid History");
        
        createLabelRow( 1, "FB" );
        createLabelRow( 2, "VRTU" );
        createLabelRow( 3, "MSFT" );
        createLabelRow( 4, "GOOGL" );
        createLabelRow( 5, "YHOO" );
        createLabelRow( 6, "XLNX" );
        createLabelRow( 7, "TSLA" );
        createLabelRow( 8, "TXN" );

        add( display, BorderLayout.CENTER);
        add( bidHistory, BorderLayout.SOUTH);
        timer = new Timer(500, this); 
        timer.start(); 
    
    }

    public void setHeader( int i, String FieldName ){
        
        labels[i]=new JLabel(FieldName);
        labels[i].setFont(new Font("Serif", Font.BOLD, 18));
        labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labels[i].setHorizontalAlignment(SwingConstants.CENTER);
        display.add(labels[i]);
    
    }

    public void createLabelRow( int i ,String symbol) {
        
        label[ (i-1)*3 ] = new Label( labels[3*i+1], display, symbol );
 		labels[3*i+1] = label[ (i-1)*3 ].jlabel;
         
        label[ (i-1)*3 + 1 ] = new Label( labels[3*i+2], display, SecurityName(symbol) );
 		labels[3*i+2] = label[ (i-1)*3 +1 ].jlabel;
         
        label[ (i-1)*3 + 2 ] = new Label( labels[3*i+3], display, Price(symbol) );
 		labels[3*i+3] = label[ (i-1)*3 +2 ].jlabel;
         
        button[i-1] = new Button( showButton[i-1], display, symbol );
        showButton[i-1] = button[i-1].jbutton;

    }

    public void actionPerformed(ActionEvent e) {
        String timeStamp;
        for( Client s: MainServer.clientList ){
            timeStamp = new SimpleDateFormat("EEE, MMM d, yyyy 'at' h:mm a").format(Calendar.getInstance().getTime()); //get system time and date
            if(s.newBidState){
                StocksDB.setBidLog( s.clientName, s.symbol, s.bidPrice, timeStamp );
                textArea.append(timeStamp + " : " + s.clientName + " set a Bid of "+ Price( s.symbol ) +" on "+ s.symbol  +".\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
                
                for( int j=0; j<8; j++ ){
                    if( s.symbol.equals( label[3*j].jlabel.getText() ) ){
                        labels[ (j+2)*3 ].setText( Price(s.symbol) ); 
                        setSymbolLogs(j,s.symbol);   
                    }
                }
                s.newBidState=false;
            }          
        }

        if( e.getSource() instanceof JButton ) {
            String buttonSymbol = ((JButton)e.getSource()).getText();

            for( int i = 0; i<8; i++){   
                if( buttonSymbol.equals( label[3*i].jlabel.getText() ) ){
                    JOptionPane.showMessageDialog(null, BidLog[i]+"\n" , "All Bids for " + buttonSymbol, JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    public void setSymbolLogs(int k, String symbol ) {
        int length = StocksDB.stockLog.get( symbol ).size();
        BidLog[k] = new ArrayList<>();
        for(int i =0; i< length;i++){
            BidLog[k].add(StocksDB.stockLog.get(symbol).get(i).details());
        }
    }

    public String SecurityName( String key ){
        return StocksDB.stocksDetails.get(key).getName();
    }

    public String Price( String key ){
        return  Float.toString(StocksDB.stocksDetails.get(key).getPrice());
    }
}