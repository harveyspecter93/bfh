import java.net.*;
import java.io.*;

class ServerClientThread extends Thread {
	Socket serverClient;
	int clientNo;

	ServerClientThread(Socket inSocket, int counter) {
		serverClient = inSocket;
		clientNo = counter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			String clientMessage = "";
			while (true) {
				clientMessage = inStream.readUTF();
				System.out.println("From Client-" + clientNo + ": Message is :" + clientMessage);
				if(clientMessage.equals("bye")) {
					outStream.writeUTF("From Server to Client-" + clientNo + " thanks for talking to me. Bye!");
					break;
				}
				doActionBasedOnMessage(clientMessage, outStream);
				outStream.flush();
			}
			inStream.close();
			outStream.close();
			serverClient.close();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.out.println("Client -" + clientNo + " exit!! ");
		}
	}

	private void doActionBasedOnMessage(String clientMessage, DataOutputStream outStream) throws IOException {
		if (clientMessage.matches(".*\\d.*")) {
			doSqaure(clientMessage, outStream);
			return;
		}
		
		String clientMessageLowerCase = clientMessage.toLowerCase();
		
		switch (clientMessageLowerCase) {
		case "ping":
			doPingPong(outStream);
			break;
		default:
			answer(clientMessage, outStream);
			break;
		}
		return;
	}

	private void answer(String clientMessage, DataOutputStream outStream) throws IOException {
		outStream.writeUTF("From Server to Client-" + clientNo + " no special Action for message: " + clientMessage + " try ping, a Number or bye!");
	}

	private void doSqaure(String clientMessage, DataOutputStream outStream) throws IOException {
		int squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
		String serverMessage = "From Server to Client-" + clientNo + " Square of entered number (" + clientMessage + ") is "
				+ squre;
		outStream.writeUTF(serverMessage);

	}

	private void doPingPong(DataOutputStream outStream) throws IOException {
		outStream.writeUTF("From Server to Client-" + clientNo + ": Pong!");

	}
}