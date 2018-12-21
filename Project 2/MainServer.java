
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.util.*; 

public class MainServer{
    
    public static ArrayList<Client> clientList = new ArrayList<>();
    public static final int BASE_PORT = 2000;
    public StocksDB stockDetails=null;     
    private static ServerSocket serverSoc; 
    
    public MainServer( StocksDB stock ) throws IOException { 
		serverSoc = new ServerSocket(BASE_PORT);
		this.stockDetails = stock;
    }
    
    public void serverLoop() throws IOException { 
		while(true) {
		    Socket socket = serverSoc.accept(); 	    
		    Client client = new Client( socket, stockDetails );
		    Thread clientThread = new Thread( client );
		    clientList.add( client );
		    clientThread.start(); 	    
		}
    }
    
    public static void main(String[] args)  throws IOException{

        StocksDB stockItems = new StocksDB("stocks.csv");
        MainServer server = new MainServer(stockItems);

        JFrame frame = new JFrame("Current Stock Prices");
        frame.setSize(600, 600);       
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  		//frame.add(new Display(server));
  		frame.pack();
        frame.setVisible(true);

		server.serverLoop();  

    }
}
