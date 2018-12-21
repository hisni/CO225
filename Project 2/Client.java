import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import java.util.*;

class Client implements Runnable, ActionListener {

    public static final int AUTH_NAME = 0;
    public static final int GET_SYMBOL = 1;
    public static final int GET_BID_PRICE = 2;

    private Socket connectedSocket; 
    private StocksDB stocks;
    private int currentState;
    private float bidPrice;
    public String  clientName;
    public String symbol=null;
    public boolean newbid=false;

    public void actionPerformed(ActionEvent e) {

    }

    public Client( Socket socket, StocksDB stockDB ) throws IOException { 
		connectedSocket = socket;
		stocks = stockDB;
    }

    public float getStockPrice( String key ){
        return StocksDB.stocksDetails.get(key).getPrice();
    }

    public void run(){
		try {

            BufferedReader in = new BufferedReader(new InputStreamReader(this.connectedSocket.getInputStream()));
		    PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectedSocket.getOutputStream()));
		    String line; 
            out.print("WELCOME TO AUCTION SERVER\n");
            out.print("Enter Client Name : ");
            out.flush();
            currentState = AUTH_NAME;

            for(line = in.readLine(); line != null && !line.equals("quit"); line = in.readLine()) {
                switch (currentState) {
                    case AUTH_NAME:
                        clientName = line;
                        out.print("\nEnter the Symbol of Bidding item : ");
                        currentState = GET_SYMBOL;
                        break;

                    case GET_SYMBOL :
                        symbol = line;
                        if ( StocksDB.stocksDetails.containsKey(symbol) ){
                            currentState = GET_BID_PRICE;
                            out.print("\nBidding Item :"+symbol + "\nCurrent price is "+ getStockPrice(symbol));
                            out.print("\nEnter Your bidding amount: ");
                        }
                        else{
                            currentState = GET_SYMBOL;
                            out.print("Security Symbol does not exists, Enter a valid Symbol.\n");
                        }
                        break;

                    case GET_BID_PRICE:
                        currentState = GET_BID_PRICE;
                        bidPrice = Float.parseFloat(line);

                        if( bidPrice > getStockPrice( symbol) ){
                            StocksDB.setPrice(symbol, bidPrice, clientName);
                            out.print("You set a bid of " + bidPrice + " on "+ symbol + ".");
                            out.print("\nEnter Your new bidding amount: ");
                        }
                        else{
                            out.print("Your Bid is not accepted. Bid higher than " + getStockPrice(symbol) );
                            out.print("\nEnter Your new bidding amount: ");
                        }
                        break;
                    default:
                        out.println("Undefined state");
                        return;
                }
                out.flush();
            }
            out.close();
            in.close();
            this.connectedSocket.close();

        }catch (IOException e) { 
		    System.out.println(e); 
		}finally { 	    
            try{
                this.connectedSocket.close(); 
            }catch(Exception e){}	
        }
    }

}