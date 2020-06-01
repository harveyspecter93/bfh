 
import java.net.*;
import java.io.*;
public class TCPClient {
  /**
 * @param args
 * @throws Exception
 */
public static void main(String[] args) throws Exception {
  int amountOfRequests = 1; //je nach Testfall anpassen
	  try{
    Socket socket=new Socket("192.168.1.22", 1337); // Server adresse + Port
    DataInputStream inStream=new DataInputStream(socket.getInputStream());
    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String clientMessage="",serverMessage="";
    while(!clientMessage.equals("bye")){
      System.out.println("You can talk to the Server now.");
      System.out.println("Enter a Number, \"Ping\" or \"Bye\"");
      clientMessage=br.readLine();
      
      for (int i = 0; i < amountOfRequests; i++) { 
    	  outStream.writeUTF(clientMessage);
          outStream.flush();
          serverMessage=inStream.readUTF();
          System.out.println(serverMessage);
      }
    }
    outStream.close();
    outStream.close();
    socket.close();
  }catch(Exception e){
    System.out.println(e);
  }
  }
}
